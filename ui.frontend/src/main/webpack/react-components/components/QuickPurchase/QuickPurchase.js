import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import QuickPurchaseForm from "./QuickPurchaseForm/QuickPurchaseForm";
import ScooterInfo from "../ScooterInfo/ScooterInfo";
import loginUtils from "../../../site/scripts/utils/loginUtils";
import { useGetAllProducts } from "../../hooks/preBooking/preBookingHooks";
import { getProductPricesData } from "../../services/productDetails/productDetailsService";
import Logger from "../../../services/logger.service";
import { setSpinnerActionDispatcher } from "../../store/spinner/spinnerActions";
import currencyUtils from "../../../site/scripts/utils/currencyUtils";
import appUtils from "../../../site/scripts/utils/appUtils";
import { useQuickPurchasePayment } from "../../hooks/quickPurchase/quickPurchaseHooks";
import analyticsUtils from "../../../site/scripts/utils/analyticsUtils";
import { getCityListForDealers } from "../../../services/location.service";
import OtpForm from "../OtpForm/OtpForm";
import Popup from "../Popup/Popup";
import {
  cryptoUtils,
  RSAUtils
} from "../../../site/scripts/utils/encryptDecryptUtils";
import {
  useGenerateOTP,
  useVerifyOTP,
  useTestDriveSendOtp
} from "../../hooks/userAccess/userAccessHooks";
import { getUtmParams } from "../../../react-components/services/utmParams/utmParams";
import {
  setUserFormDataAction,
  // setUserCheckAction,
  setUserStatusAction,
  setUserCheckActionDispatcher
} from "../../store/userAccess/userAccessActions";

const QuickPurchase = (props) => {
  const {
    config,
    selectedScooterData,
    userProfileData,
    sfid,
    setUserAccessInfo
  } = props;
  const defaultCityList = appUtils.getConfig("cityList");
  const defaultCountry = appUtils.getConfig("defaultCountry");
  const [cityList, setCityList] = useState(defaultCityList);
  const [defaultCityValue, setDefaultCityValue] = useState("");
  const [activeVariant, setActiveVariant] = useState(0);
  const [handleVariantCalled, setHandleVariantCalled] = useState();
  const defaultCountryCode = appUtils.getConfig("defaultCountryCode");
  const {
    scooterInfo,
    errorMessagesSkipped,
    whatsappConsent = false,
    submitWaitingMessage,
    scooterColorCheck
  } = config;
  const [showRegisterError, setShowRegisterError] = useState("");
  const [submittedFormData, setSubmittedFormData] = useState(null);
  const [showOtpError, setShowOtpError] = useState("");

  const [colorName, setColorName] = useState("");
  const [showEmailverify, setShowEmailverify] = useState(false);
  const [showEmailOtp, setShowEmailOtp] = useState(false);
  const [isLogin, setIsLogin] = useState(false);
  const [selectedDealerData, setSelectedDealerData] = useState(null);
  const [isRegisteredUser, setIsRegisteredUser] = useState(null);
  const verifyOTP = useVerifyOTP(false);
  const [showVariantColorPopUp, setShowVariantColorPopUp] = useState(false);

  console.log("===colorName", colorName);

  const isLoggedIn = loginUtils.isSessionActive();
  console.log("===isLoggedIn", isLoggedIn);
  const [scooterData, setScooterData] = useState(null);
  const [isOtpSubmission, setIsOtpSubmission] = useState(false);
  const [submittedData, setSubmittedData] = useState(false);
  const [isActiveScooterModel, setActiveScooterModel] = useState(
    scooterData?.products?.items[0]?.sku || ""
  );
  const [priceList, setPriceList] = useState([]);
  const [isUserDataAvailable, setUserDataAvailable] = useState(false);
  const [isUpdatedCityState, setUpdatedCityState] = useState("");
  const [showPopup, setShowPopup] = useState(false);

  const getAllProductData = useGetAllProducts();
  const generateLoginOTP = useGenerateOTP(true, false);
  const getProductPriceList = async () => {
    const result = await getProductPricesData();
    setPriceList(result);
  };

  const fetchCityList = async () => {
    setSpinnerActionDispatcher(true);
    const cityListRes = await getCityListForDealers(defaultCountry);
    if (cityListRes.length > 0) {
      setCityList([...defaultCityList, ...cityListRes]);
    }
  };
  useEffect(() => {
    fetchCityList();
  }, []);

  const getAllProductsData = async (selectedCityStateId) => {
    try {
      setSpinnerActionDispatcher(true);
      const allProductsData = await getAllProductData({
        variables: {
          category_id: 2
        }
      });
      if (allProductsData) {
        allProductsData.data.products.items =
          allProductsData.data.products.items.filter(function (item) {
            return item.variants.length > 0;
          });
        let cityStateId = "";
        if (isLoggedIn) {
          let isPriceNotAvailable = false;
          if (
            isUserDataAvailable &&
            userProfileData.city &&
            userProfileData.state &&
            userProfileData.country
          ) {
            if (isUpdatedCityState !== "") {
              cityStateId = isUpdatedCityState;
              setUpdatedCityState("");
            } else {
              cityStateId =
                userProfileData.city +
                config.scooterInfo.splitterChar +
                userProfileData.state +
                config.scooterInfo.splitterChar +
                userProfileData.country;
            }
            if (selectedCityStateId) {
              cityStateId = selectedCityStateId;
            }
            allProductsData.data.products.items.map((model) => {
              model.variants.map((variant) => {
                priceList.map((item) => {
                  if (
                    item.city_state_id.toLowerCase() ===
                      cityStateId.toLowerCase() &&
                    item.variant_sku === variant.product.sku
                  ) {
                    isPriceNotAvailable = true;
                  }
                });
              });
            });

            if (!isPriceNotAvailable) {
              cityStateId = config.scooterInfo.defaultCityState;
            }
          } else {
            cityStateId = config.scooterInfo.defaultCityState;
          }
        } else {
          if (selectedCityStateId) {
            cityStateId = selectedCityStateId;
          } else {
            cityStateId = config.scooterInfo.defaultCityState;
          }
        }
        allProductsData.data.products.items.map((model) => {
          model.variants.map((variant) => {
            priceList.map((item) => {
              if (
                item.city_state_id.toLowerCase() ===
                  cityStateId.toLowerCase() &&
                item.variant_sku === variant.product.sku
              ) {
                variant.product["price"] = currencyUtils.getCurrencyFormatValue(
                  item.effectivePrice
                );
                variant.product["city"] =
                  cityStateId &&
                  cityStateId.split(config.scooterInfo.splitterChar)[0];
              }
            });
          });
        });
        setScooterData(allProductsData.data);
        setActiveScooterModel(allProductsData.data.products.items[0].sku);
      }
    } catch (error) {
      Logger.error(error.message);
    }
  };

  const handleActiveScooter = (productData) => {
    setActiveScooterModel(productData.sku);
  };

  useEffect(() => {
    getProductPriceList();
  }, []);

  useEffect(() => {
    if (priceList.length > 0) {
      if (isLoggedIn && isUserDataAvailable) {
        getAllProductsData();
      } else {
        getAllProductsData();
      }
    }
  }, [priceList, isUserDataAvailable]);

  useEffect(() => {
    if (
      userProfileData.fname ||
      (userProfileData.city && userProfileData.state && userProfileData.country)
    ) {
      setUserDataAvailable(true);
    }
  }, [userProfileData]);

  useEffect(() => {
    if (
      userProfileData.city &&
      userProfileData.city.length > 0 &&
      cityList.length > 1
    ) {
      const userCity = cityList.find(
        (city) => city.city === userProfileData.city
      );
      userCity && setDefaultCityValue(userCity.value);
    }
  }, [userProfileData.city, cityList.length]);

  const generateRegisterOTP = useTestDriveSendOtp(false, false);
  //const generateRegisterOTP = useGenerateOTP(false, false);
  const isAnalyticsEnabled = analyticsUtils.isAnalyticsEnabled();
  const payForQuickPurchase = useQuickPurchasePayment();
  const handleQuickPurchaseSubmission = async (submittedData) => {
    setShowPopup(true);
    // setSpinnerActionDispatcher(true);
    const selectedCity = cityList.find(
      (city) => city.value === submittedData.city
    );
    console.log("===submittedData.partnerId", submittedData.partnerId);
    const params = getUtmParams();
    const data = {
      branchId: selectedDealerData.branchId,
      firstname: submittedData.fname,
      lastname: submittedData.lname,
      email: submittedData.email,
      mobile: submittedData.phoneNumber,
      city: selectedCity.city,
      state: selectedCity.state,
      pincode: selectedDealerData.pincode,
      skuId: selectedScooterData.selectedVariant.product.sf_id,
      itemId: selectedScooterData.sf_id,
      partnerAccountId: selectedDealerData.partnerId,
      utm_params: params
    };
    console.log("===selectedCity", selectedCity);
    console.log("===selectedScooterData", selectedScooterData);
    // const encryptedData = RSAUtils.encrypt(data);
    const quickPurchasePaymentRes = await payForQuickPurchase({
      variables: {
        input: data
      }
    });
    console.log("===quickPurchasePaymentRes", quickPurchasePaymentRes);
    if (
      quickPurchasePaymentRes?.data?.quickPurchase?.success &&
      quickPurchasePaymentRes?.data?.quickPurchase?.opportunityId &&
      quickPurchasePaymentRes?.data?.quickPurchase?.saleOrderId
    ) {
      const aadharVerificationUrl = appUtils.getPageUrl(
        "aadharVerificationUrl"
      );
      const opportunityId =
        quickPurchasePaymentRes?.data?.quickPurchase?.opportunityId;
      const saleOrderId =
        quickPurchasePaymentRes?.data?.quickPurchase?.saleOrderId;

      const encryptParams = [
        "orderId=",
        saleOrderId,
        "&opportunityId=",
        opportunityId
      ].join("");
      const encryptedParams = cryptoUtils.encrypt(encryptParams);
      const redirectUrl = aadharVerificationUrl + "?" + encryptedParams;
      setShowPopup(false);
      if (!isAnalyticsEnabled) {
        const location = {
          state: selectedCity.state,
          city: selectedCity.city,
          pinCode: "",
          country: defaultCountry
        };
        const startingPrice = selectedScooterData.selectedVariant.product.price;
        const productDetails = {
          modelVariant: selectedScooterData.name,
          modelColor: selectedScooterData.selectedVariant.attributes[0].label,
          productID: selectedScooterData.selectedVariant.product.sf_id,
          startingPrice: parseFloat(startingPrice.replace(/[₹\,]/g, ""))
        };
        const customLink = {
          name: event.nativeEvent.submitter.innerText,
          position: "Bottom",
          type: "Button",
          clickType: "other"
        };
        analyticsUtils.trackQuickPurchaseInit(
          location,
          productDetails,
          customLink,
          function () {
            window.location.href = redirectUrl;
          }
        );
      } else {
        window.location.href = redirectUrl;
      }
    } else {
      setShowPopup(false);
    }
  };

  const handleFormSubmit = async (formData, event) => {
    try {
      //get otp first
      setSubmittedFormData(formData);

      if (isLoggedIn) {
        handleQuickPurchaseSubmission(formData);
      } else {
        setSpinnerActionDispatcher(true);
        setUserAccessInfo({
          countryCode: formData.countryCode || defaultCountryCode,
          numberOrEmail: formData.phoneNumber || "",
          mobileNumber: formData.phoneNumber,
          fname: formData.fname || "",
          lname: formData.lname || "",
          email: formData.email || "",
          customer_city: formData.city || "",
          customer_state: formData.state || "",
          customer_country: defaultCountry || ""
        });
        const variables = {
          country_code: "+91",
          mobile_number: RSAUtils.encrypt(formData.phoneNumber),
          email: RSAUtils.encrypt(formData.email),
          is_login: false,
          source: "testdrive"
        };
        const loginResult = await generateRegisterOTP({
          variables
        });
        setSpinnerActionDispatcher(false);
        if (loginResult) {
          if (loginResult.data) {
            if (isAnalyticsEnabled) {
              const customLink = {
                name: event.target.innerText,
                position: "Bottom",
                type: "Button",
                clickType: "other"
              };

              analyticsUtils.trackLoginOTPClick(customLink);
              analyticsUtils.trackLoginPageView();
            }
            setShowOtpError("");
            window.scrollTo(0, document.body.scrollHeight);
            // showEmailverify && setShowEmailverify(false)

            setIsOtpSubmission(true);
          } else if (loginResult.errors && loginResult.errors.message) {
            if (errorMessagesSkipped.includes(loginResult.errors.message)) {
              setIsOtpSubmission(true);
            } else {
              setShowRegisterError(loginResult.errors.message);
            }
          } else {
            Logger.error(loginResult);
          }
        }
      }

      //end get otp first
      //then login if needed
      //tjhen submit values
    } catch (error) {
      Logger.error(error);
    }
  };

  const handleCityDropdownChange = (stateValue, cityValue) => {
    if (cityValue !== "") {
      const userCity = cityList.find((city) => city.city === cityValue);
      const selectedCityStateId = (
        userCity.city +
        config.scooterInfo.splitterChar +
        userCity.state +
        config.scooterInfo.splitterChar +
        ((userProfileData && userProfileData.country) || defaultCountry)
      ).toUpperCase();
      getAllProductsData(selectedCityStateId);
    }
  };

  //OTP verification API call with data
  const handleVerifyOTP = async (event, otp) => {
    try {
      setSpinnerActionDispatcher(true);
      setShowOtpError("");

      const selectedCity = cityList.find(
        (city) => city.value === submittedFormData.city
      );

      const params = getUtmParams();
      let variables = {
        SF_ID: sfid,
        otp: RSAUtils.encrypt(otp),
        country_code: "+91",
        customer_city: selectedCity.city,
        customer_state: selectedCity.state,
        customer_country: defaultCountry,
        utm_params: params
      };

      if (!isLogin) {
        if (showEmailOtp) {
          variables = {
            ...variables,
            ...{
              fname: submittedFormData.fname,
              lname: submittedFormData.lname,
              mobile_number: RSAUtils.encrypt(submittedFormData.phoneNumber),
              email: RSAUtils.encrypt(submittedFormData.email)
            }
          };
        } else {
          variables = {
            ...variables,
            ...{
              fname: submittedFormData.fname,
              lname: submittedFormData.lname,
              mobile_number: RSAUtils.encrypt(submittedFormData.phoneNumber),
              email: RSAUtils.encrypt(submittedFormData.email),
              whatsapp_consent: submittedFormData.subscribe,
              is_login: isLogin
            }
          };
        }
      } else {
        variables = {
          ...variables,
          ...{
            username: RSAUtils.encrypt(numberOrEmail),
            is_login: isLogin
          }
        };
      }
      const loginResult = await verifyOTP({
        variables
      });

      if (loginResult && loginResult.data) {
        if (loginResult.data.VerifyOtp.status_code === 200) {
          if (loginResult.data.VerifyOtp.pinNo) {
            Cookies.set(
              CONSTANT.COOKIE_PIN_NUMBER,
              loginResult.data.VerifyOtp.pinNo,
              {
                expires: appUtils.getConfig("tokenExpirtyInDays"),
                secure: true,
                sameSite: "strict"
              }
            );
          }
          const currentUrl = new URL(window.location.href);
          const redirectionUrl =
            currentUrl.search && currentUrl.search.split("?redirectURL=")[1];
          handleQuickPurchaseSubmission(submittedFormData);

          // For Signup
          //  setShowEmailverify(true);
          setLoginStatus();
          if (isAnalyticsEnabled) {
            if (showEmailOtp) {
              analyticsUtils.trackSignupComplete(function () {
                window.location.href = redirectionUrl
                  ? redirectionUrl
                  : appUtils.getPageUrl("profileUrl");
              });
            } else {
              const customLink = {
                name: event.target.innerText,
                position: "Bottom",
                type: "Button",
                clickType: "other"
              };
              analyticsUtils.trackSignupOTPClick(customLink);
            }
          } else {
            window.location.href = redirectionUrl
              ? redirectionUrl
              : appUtils.getPageUrl("profileUrl");
          }
        }
      } else {
        setShowOtpError(loginResult.errors.message);
      }
    } catch (error) {
      Logger.error(error);
    }
  };

  const handleChangeNumber = (event) => {
    if (isAnalyticsEnabled) {
      const customLink = {
        name: event.currentTarget.innerText,
        position: "Middle",
        type: "Link",
        clickType: "other"
      };

      analyticsUtils.trackLoginOTPClick(customLink);
      analyticsUtils.trackLoginPageView();
    }
    setIsOtpSubmission(false);
  };
  const handleCancelEmailVerification = (e) => {
    const currentUrl = new URL(window.location.href);
    const redirectionUrl =
      currentUrl.search && currentUrl.search.split("?redirectURL=")[1];

    if (isAnalyticsEnabled) {
      const customLink = {
        name: e.target.innerText,
        position: "Bottom",
        type: "Link",
        clickType: "other"
      };
      const additionalPageName = ":Verify OTP";
      const additionalJourneyName = "";
      analyticsUtils.trackCtaClick(
        customLink,
        additionalPageName,
        additionalJourneyName,
        function () {
          window.location.href = redirectionUrl
            ? redirectionUrl
            : appUtils.getPageUrl("profileUrl");
        }
      );
    } else {
      window.location.href = redirectionUrl
        ? redirectionUrl
        : appUtils.getPageUrl("profileUrl");
    }
  };

  const handleSendEmailOtp = (data, event) => {
    handleGenerateOTP({ data, ...submittedFormData }, event);
    setShowEmailOtp(true);
  };

  return (
    <div className="vida-container">
      <div className="vida-quick-purchase">
        <div className="vida-quick-purchase__container">
          <div className="vida-quick-purchase__scooter-info">
            {scooterData?.products?.items.map((product, index) => (
              <ScooterInfo
                scooterInfoConfig={scooterInfo}
                isImgLeftLayout={true}
                productData={product}
                activeScooterHandler={handleActiveScooter}
                key={index}
                isActiveScooterModel={
                  product.sku == isActiveScooterModel ? true : false
                }
                defaultSelection={0}
                selectedScooterData={isLoggedIn && selectedScooterData}
                handleVariantCalled={handleVariantCalled}
                setHandleVariantCalled={setHandleVariantCalled}
                activeVariantParent={activeVariant}
                setActiveVariantParent={setActiveVariant}
                setColorName={setColorName}
              ></ScooterInfo>
            ))}
          </div>

          <div className="vida-quick-purchase__quick-form">
            <QuickPurchaseForm
              config={config}
              userData={userProfileData}
              handleCityDropdownChange={handleCityDropdownChange}
              handleFormSubmit={(formData, event) => {
                setSubmittedData({ formData, event });

                setShowVariantColorPopUp(true);
              }}
              cityList={cityList}
              showRegisterError={showRegisterError}
              setSelectedDealerData={setSelectedDealerData}
              isPurchaseButtonDisabled={isOtpSubmission}
            />

            {isOtpSubmission ? (
              <div className="vida-user-access__otp-container">
                <OtpForm
                  otpConfig={config.otpConfig}
                  verifyOTPHandler={handleVerifyOTP}
                  changeNumberHandler={handleChangeNumber}
                  isLogin={isLogin}
                  showError={showOtpError}
                  //activeTab={activeTab}
                  showEmailverify={showEmailverify}
                  showEmailOtp={showEmailOtp}
                  cancelEmailVerification={handleCancelEmailVerification}
                  sendEmailOtp={handleSendEmailOtp}
                  isPlainLayout={true}
                ></OtpForm>
              </div>
            ) : (
              ""
            )}
          </div>
          {showPopup && (
            <div className="vida-quick-purchase__submission_popup">
              <Popup mode="small" hideClose={true}>
                <h3 className="vida-quick-purchase__submission_popup-container">
                  {submitWaitingMessage}
                </h3>
              </Popup>
            </div>
          )}

          {showVariantColorPopUp && (
            <div className="vida-pricing-new vida-pricing-new__popup-wrapper vida-pricing-new__subsidycheck-popup">
              <Popup
                mode="medium"
                handlePopupClose={() => setShowVariantColorPopUp(false)}
              >
                <div className="vida-pricing-new__popup">
                  <h4>{scooterColorCheck.title}</h4>
                  <p>
                    {scooterColorCheck.description} {colorName}
                  </p>

                  <div className="vida-pricing-new__insure-btn-container">
                    <button
                      className="btn btn--primary"
                      onClick={(event) => {
                        handleFormSubmit(
                          submittedData.formData,
                          submittedData.event
                        );
                        setShowVariantColorPopUp(false);
                      }}
                    >
                      {scooterColorCheck.yesBtn.label}
                    </button>
                    <button
                      className="btn btn--secondary"
                      onClick={(event) => {
                        setShowVariantColorPopUp(false);
                      }}
                    >
                      {scooterColorCheck.noBtn.label}
                    </button>
                  </div>
                </div>
              </Popup>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

const mapDispatchToProps = (dispatch) => {
  return {
    setPreBookingInfo: (data) => {
      dispatch(setPreBookingDataAction(data));
    },
    setUserAccessInfo: (data) => {
      dispatch(setUserFormDataAction(data));
    },
    // setUserCheckInfo: (data) => {
    //   dispatch(setUserCheckAction(data));
    // },
    setUserStatus: (status) => {
      dispatch(setUserStatusAction(status));
    },
    setScooterInfo: (data) => {
      dispatch(setScooterInfoAction(data));
    }
  };
};

const mapStateToProps = ({
  scooterInfoReducer,
  userAccessReducer,
  userProfileDataReducer
}) => {
  return {
    sfid: userAccessReducer.sfid,
    selectedScooterData: {
      name: scooterInfoReducer.name,
      sku: scooterInfoReducer.sku,
      sf_id: scooterInfoReducer.sf_id,
      variants: scooterInfoReducer.variants,
      selectedVariant: scooterInfoReducer.selectedVariant
    },
    userProfileData: {
      fname: userProfileDataReducer.fname,
      lname: userProfileDataReducer.lname,
      email: userProfileDataReducer.email,
      number: userProfileDataReducer.number,
      city: userProfileDataReducer.city,
      state: userProfileDataReducer.state,
      country: userProfileDataReducer.country
    }
  };
};

QuickPurchase.propTypes = {
  sfid: PropTypes.string,
  config: PropTypes.object,
  selectedScooterData: PropTypes.object,
  userProfileData: PropTypes.object,
  setUserAccessInfo: PropTypes.func
};

QuickPurchase.defaultProps = {
  config: {}
};
export default connect(mapStateToProps, mapDispatchToProps)(QuickPurchase);
