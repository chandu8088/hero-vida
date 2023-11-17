import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { useForm } from "react-hook-form";
import InputField from "../../form/InputField/InputField";
import Dropdown from "../../form/Dropdown/Dropdown";
import PhoneNumber from "../../form/PhoneNumber/PhoneNumber";
import appUtils from "../../../../site/scripts/utils/appUtils";
import loginUtils from "../../../../site/scripts/utils/loginUtils";
import { setSpinnerActionDispatcher } from "../../../store/spinner/spinnerActions";
import {
  getCityListForDealers,
  getVidaCentreList
} from "../../../../services/location.service";
import { setPreBookingUserDataAction } from "../../../store/preBooking/preBookingActions";
import { setUserFormDataActionDispatcher } from "../../../store/userAccess/userAccessActions";

import CONSTANT from "../../../../site/scripts/constant";

const QuickPurchaseForm = (props) => {
  const {
    config,
    userData,
    handleCityDropdownChange,
    handleFormSubmit,
    nearByVidaCentreList,
    selectedPinCode,
    prebookingState,
    prebookingCity,
    branchId,
    cityList,
    showRegisterError,
    setSelectedDealerData,
    isPurchaseButtonDisabled = false
  } = props;
  const { genericConfig, stateField, cityField, notificationField } =
    config || {};
  const [isSubscribed, setIsSubscribed] = useState(true);
  const isLoggedIn = loginUtils.isSessionActive();
  const defaultCountryCode = appUtils.getConfig("defaultCountryCode");
  const codeList = appUtils.getConfig("countryCodes");
  const [agreeTermsSelected, setAgreeTermsSelected] = useState(false);
  const [showAgreeTermError, setShowAgreeTermError] = useState(false);
  const [isFormValid, setIsFormValid] = useState(false);
  const [showTermsPopup, setShowTermsPopup] = useState(false);
  const [termsContent, setTermsContent] = useState(null);

  const customCheckBoxKeyPress = (event) => {
    const isEnter = event.which === 13;
    if (isEnter) {
      event.preventDefault();
      setIsSubscribed(!isSubscribed);
    }
  };

  const handleOnSubscribeChange = () => {
    setIsSubscribed(!isSubscribed);
  };

  const {
    register,
    handleSubmit,
    setValue,
    setError,
    clearErrors,
    getValues,
    formState: { errors }
  } = useForm({
    mode: "onSubmit",
    reValidateMode: "onChange"
  });

  const handleInputChange = (fieldname, value) => {
    if (value === "") {
      setError(fieldname, {
        type: "required"
      });
    } else if (
      value.length < config.firstNameField.validationRules.minLength.value &&
      fieldname === "fname"
    ) {
      setError("fname", {
        type: "custom",
        message: config.firstNameField.validationRules.minLength.message
      });
    } else if (
      !isLoggedIn &&
      !CONSTANT.EMAIL_REGEX.test(value) &&
      fieldname === "email"
    ) {
      setError("email", {
        type: "custom",
        message: config.emailField.validationRules.customValidation.message
      });
    } else {
      clearErrors(fieldname);
    }
  };

  const toggleTermsCheck = (event) => {
    setAgreeTermsSelected(event.target.checked);
    setIsFormValid(event.target.checked);
    setShowAgreeTermError(!event.target.checked);
  };

  const handleTermsandConditions = (event) => {
    event.preventDefault();
    setShowTermsPopup(true);
    document.querySelector("html").classList.add("overflow-hidden");
    const content = document.getElementById(config.agreeTerms.id);
    setTermsContent(content.innerHTML);
  };

  const closeTermsPopup = () => {
    setShowTermsPopup(false);
    document.querySelector("html").classList.remove("overflow-hidden");
  };

  const handleAgreeTerms = () => {
    setAgreeTermsSelected(true);
    setShowAgreeTermError(false);
    setIsFormValid(true);
    closeTermsPopup();
  };

  useEffect(() => {
    userData.fname && setValue("fname", userData.fname);
    userData.lname && setValue("lname", userData.lname);
    userData.email && setValue("email", userData.email);
    userData.number && setValue("phoneNumber", userData.number);
  }, [userData]);

  // state city logic similar to booking details by dealers

  const stateFieldInfo = {
    name: "state",
    options: appUtils.getConfig("stateSearchList"),
    ...stateField
  };
  const [dataList, setDataList] = useState({});
  const cityFieldInfo = {
    name: "city",
    options: appUtils.getConfig("cityList"),
    ...cityField
  };

  const [stateFieldData, setStateFieldData] = useState({
    options: [],
    value: userData.state || "",
    isDisabled: false
  });
  const [cityFieldData, setCityFieldData] = useState({
    options: [],
    value: "",
    isDisabled: true
  });
  const [selectedCity, setSelectedCity] = useState("");

  const [listOfDealers, setListOfDealers] = useState([]);

  const [selectedDealer, setSelectedDealer] = useState("");

  const [cityDropdownValue, setCityDropdownValue] = useState("");
  const [defaultCityValue, setDefaultCityValue] = useState("");
  const [noDealerSelected, setNoDealerSelected] = useState(false);
  const [noDealersAvailable, setNoDealersAvailable] = useState(false);
  const [selectedCityValue, setSelectedCityValue] = useState("");
  const [selectedStateValue, setSelectedStateValue] = useState("");
  const [responseDealers, setResponseDealers] = useState([]);
  const handleDealerClick = (dealer) => {
    setSelectedDealerData({
      pincode: dealer.postalCode,
      branchId: dealer.id,
      partnerId: dealer.accountpartnerId,
      city: selectedCityValue || cityFieldData.value,
      state: selectedStateValue
    });
    setSelectedDealer(dealer.id);
  };
  useEffect(() => {
    const dealersList = [];
    if (nearByVidaCentreList.length > 0) {
      nearByVidaCentreList.map((item) => {
        if (item.type === "Experience Center") {
          item.items.map((x) => {
            dealersList.push(
              <div
                onClick={(e) => handleDealerClick(x)}
                key={x.id}
                className={`form vida-booking-details-dealers__dealer vida-booking-details-dealers__dealer${
                  selectedDealer === x.id || branchId === x.id ? "--active" : ""
                }`}
              >
                <p>{x.experienceCenterName}</p>
                <div>{x.address}</div>
              </div>
            );
          });
        }
      });
    }

    if (selectedCity && !nearByVidaCentreList.length) {
      setNoDealersAvailable(true);
    } else {
      setNoDealersAvailable(false);
    }

    setListOfDealers(dealersList);
  }, [nearByVidaCentreList.length, responseDealers, selectedDealer]);

  const fetchCentreList = async (userCity) => {
    setSelectedCity("");
    setSpinnerActionDispatcher(true);
    const responseDealers = await getVidaCentreList(userCity.city);
    setResponseDealers(responseDealers);
    setSelectedCity(userCity.value);
    setSpinnerActionDispatcher(false);
  };

  const onChangeState = (name, value) => {
    setListOfDealers([]);
    setSelectedCityValue("");
    setNoDealersAvailable(false);
    const dataListOptions = dataList[value.toLowerCase()]
      ? dataList[value.toLowerCase()]?.map((item) => item)
      : [];
    if (value) {
      setSelectedStateValue(value);
      setCityFieldData({
        ...cityFieldData,
        isDisabled: false,
        options: [...cityFieldInfo.options, ...dataListOptions],
        value: ""
      });
    } else {
      setCityFieldData({
        ...cityFieldData,
        isDisabled: true,
        options: []
      });
      setListOfDealers([]);
    }
  };

  const handleDropdownChange = async (name, value) => {
    if (selectedCityValue !== value) {
      //  setListOfDealers([]);
      //  setNoDealersAvailable(false);
    }
    if (value !== "") {
      setSelectedCityValue(value);
      const userCity = cityList.find((city) => city.value === value);
      handleCityDropdownChange(userCity.state, userCity.city, "INDIA");
      setUserFormDataActionDispatcher({
        customer_city: userCity.city ? userCity.city : ""
      });
      if (cityDropdownValue !== value) {
        setSelectedDealer("");
        setNoDealerSelected(false);
      }
      fetchCentreList(userCity);
    }
  };

  useEffect(() => {
    const userCity = cityList.find((city) => city.city === userData.city);
    if (userCity) {
      setDefaultCityValue(userCity.value);
      setCityDropdownValue(userCity.value);
      fetchCentreList(userCity);
    }
  }, [userData.city]);
  useEffect(() => {
    const dataList = {};
    cityList.map((item) => {
      if (item.state) {
        const key = item.state.toLowerCase();
        dataList[key] ? dataList[key].push(item) : (dataList[key] = [item]);
      }
    });
    setDataList(dataList);
    setStateFieldData({
      ...stateFieldData,
      value: prebookingState ? prebookingState : stateFieldData.value,
      options: [
        ...stateFieldInfo.options,
        ...Object.keys(dataList).map((item) => {
          return {
            value: item.toLowerCase(),
            label: item.charAt(0).toUpperCase() + item.toLowerCase().slice(1)
          };
        })
      ]
    });

    const updatedCities =
      dataList && Object.keys(dataList).length > 0
        ? dataList[
            prebookingState
              ? prebookingState.toLowerCase()
              : stateFieldData.value.toLowerCase()
          ]?.map((item) => {
            return {
              label: item.city,
              value: item.value
            };
          })
        : [];

    setCityFieldData({
      ...cityFieldData,
      isDisabled: false,
      value: prebookingCity ? prebookingCity : cityFieldData.value,
      options: [
        ...cityFieldInfo.options,
        ...(updatedCities ? updatedCities : [])
      ]
    });
  }, [cityList]);

  const handleFormSubmitCheck = (formData, event) => {
    if (!selectedDealer) {
      setNoDealerSelected(true);
    } else {
      handleFormSubmit(formData, event);
    }
  };

  const cancelOverride = () => {
    setOverridePopup(false);
  };

  useEffect(() => {
    if (
      userData.state &&
      Object.keys(dataList).indexOf(userData.state.toLowerCase()) !== -1
    ) {
      setStateFieldData({
        ...stateFieldData,
        value: userData.state.toLowerCase()
      });
      let userDataCity = "";
      const cityValue = dataList[userData.state.toLowerCase()].map((item) => {
        if (item.city.toLowerCase() == userData.city.toLowerCase()) {
          userDataCity = item.value;
        }
      });

      setCityFieldData({
        ...cityFieldData,
        isDisabled: false,
        value: userDataCity,
        options: [
          ...cityFieldInfo.options,
          ...dataList[userData.state.toLowerCase()].map((item) => {
            return {
              label: item.city,
              value: item.value
            };
          })
        ]
      });
    }
  }, [userData.state]);

  useEffect(() => {
    onChangeState("state", userData.state);
    const selectedCityAvailable = cityFieldData.options.find(
      (item) => item.value.indexOf(userData.city.toLowerCase()) !== -1
    );

    if (selectedCityAvailable) {
      setCityFieldData({
        ...cityFieldData,
        value: selectedCityAvailable.value
      });
    } else {
      setCityFieldData({
        ...cityFieldData,
        value: prebookingCity ? prebookingCity : cityFieldData.value
      });
    }
  }, [stateFieldData]);

  useEffect(() => {
    if (showRegisterError) {
      setError("phoneNumber", {
        type: "custom"
      });
      setError("email", {
        type: "custom",
        message: showRegisterError
      });
    }
  }, [showRegisterError]);

  return (
    <div className="vida-quick-purchase-form">
      <div className="vida-quick-purchase-form__title">{config.title}</div>
      <form
        className="vida-quick-purchase-form__form"
        onSubmit={handleSubmit((formData, event) =>
          handleFormSubmitCheck(formData, event)
        )}
      >
        <InputField
          name="fname"
          label={config.firstNameField.label}
          placeholder={config.firstNameField.placeholder}
          validationRules={config.firstNameField.validationRules}
          onChangeHandler={handleInputChange}
          register={register}
          errors={errors}
          checkNameFormat
          setValue={setValue}
          isDisabled={isLoggedIn}
        />

        <InputField
          name="lname"
          label={config.lastNameField.label}
          placeholder={config.lastNameField.placeholder}
          validationRules={config.lastNameField.validationRules}
          onChangeHandler={handleInputChange}
          register={register}
          errors={errors}
          checkNameFormat
          setValue={setValue}
          isDisabled={isLoggedIn}
        />

        <PhoneNumber
          label={config.mobileField.label}
          fieldNames={{
            inputFieldName: "phoneNumber",
            selectFieldName: "countryCode"
          }}
          placeholder={config.mobileField.placeholder}
          options={codeList}
          values={{
            code: defaultCountryCode || "",
            number: ""
          }}
          validationRules={config.mobileField.validationRules}
          register={register}
          errors={errors}
          maxLength={config.mobileField.validationRules.maxLength.value}
          isDisabled={isLoggedIn}
        />

        <InputField
          name="email"
          label={config.emailField.label}
          infoLabel={config.emailField.infoLabel}
          placeholder={config.emailField.placeholder}
          validationRules={config.emailField.validationRules}
          register={register}
          errors={errors}
          checkEmailFormat
          onChangeHandler={handleInputChange}
          value=""
          setValue={setValue}
          isDisabled={isLoggedIn}
        />

        <div className="vida-booking-details-dealers__pincode">
          <Dropdown
            name={stateFieldInfo.name}
            label={stateFieldInfo.label}
            options={
              stateFieldData.options.length > 0
                ? stateFieldData.options
                : stateFieldInfo.options
            }
            value={stateFieldData.value.toLowerCase()}
            setValue={setValue}
            onChangeHandler={onChangeState}
            errors={errors}
            validationRules={stateFieldInfo.validationRules}
            clearErrors={clearErrors}
            isAutocomplete={true}
            register={register}
            isSortAsc={true}
          />
        </div>

        <div className="vida-booking-details-dealers__pincode">
          <Dropdown
            name={cityFieldInfo.name}
            label={cityFieldInfo.label}
            options={
              cityFieldData.options.length > 0
                ? cityFieldData.options
                : cityFieldInfo.options
            }
            value={cityFieldData.value}
            setValue={setValue}
            onChangeHandler={handleDropdownChange}
            errors={errors}
            validationRules={cityFieldInfo.validationRules}
            clearErrors={clearErrors}
            isAutocomplete={true}
            register={register}
            isSortAsc={true}
            // isDisabled={cityFieldData.isDisabled}
          />
        </div>

        <div className="form vida-booking-details-dealers__listofdealers">
          {listOfDealers &&
            listOfDealers.length > 0 &&
            listOfDealers.map((dealer) => {
              return dealer;
            })}
        </div>

        {!!selectedCityValue &&
          getValues(cityFieldInfo.name) &&
          listOfDealers.length === 0 &&
          !noDealersAvailable && <p>{genericConfig.dealersLoadingMsg}</p>}

        {!!selectedCityValue && noDealersAvailable && (
          <p className="vida-booking-details-dealers__errors">
            {genericConfig.noDealerAvailableError}
          </p>
        )}

        {noDealerSelected && !selectedDealer && (
          <p className="vida-booking-details-dealers__errors">
            {genericConfig.nonDealerSelectedError}
          </p>
        )}

        <div className="form__group form__field-checkbox vida-quick-purchase-form__terms">
          <label className="vida-quick-purchase-form__label">
            {config.agreeTerms.agreeLabel}{" "}
            <input
              type="checkbox"
              name="agreeTerms"
              htmlFor="terms"
              checked={agreeTermsSelected}
              onChange={(event) => toggleTermsCheck(event)}
            ></input>
            <span className="form__field-checkbox-mark"></span>
          </label>
          <a
            href="#"
            rel="noreferrer noopener"
            onClick={(event) => handleTermsandConditions(event)}
          >
            {config.agreeTerms.terms.label}
          </a>
        </div>
        {showAgreeTermError && (
          <div className={`${showAgreeTermError ? "form__group--error" : ""}`}>
            <p className="form__field-message">
              {config.agreeTerms.validationRules.required.message}
            </p>
          </div>
        )}
        <div className="form__group form__field-checkbox">
          <label className="form__field-label">
            {notificationField.label}
            <i className="icon-whatsapp"></i>
            <input
              tabIndex="0"
              type="checkbox"
              checked={isSubscribed}
              {...register("subscribe", {
                onChange: () => handleOnSubscribeChange()
              })}
            ></input>
            <span
              tabIndex="0"
              className="form__field-checkbox-mark"
              role="checkbox"
              aria-checked="false"
              onKeyPress={(e) => customCheckBoxKeyPress(e)}
            ></span>
          </label>
          <div className="vida-quick-purchase-form__notification-msg">
            {notificationField.message}
          </div>
        </div>

        {!isPurchaseButtonDisabled && (
          <div className="vida-quick-purchase-form__action">
            <button
              type="submit"
              className="btn btn--primary"
              onClick={handleSubmit}
              disabled={!isFormValid}
            >
              {config.paymentBtn.label}
            </button>
          </div>
        )}
      </form>

      {showTermsPopup && (
        <div className="vida-terms-conditions">
          <div className="vida-terms-conditions__container">
            <div className="vida-terms-conditions__body">
              <div className="vida-terms-conditions__body-wrap">
                <div
                  dangerouslySetInnerHTML={{
                    __html: termsContent
                  }}
                ></div>
              </div>
            </div>
            <div className="vida-terms-conditions__btn-wrap">
              <button
                className="btn btn--primary"
                role="button"
                onClick={() => handleAgreeTerms()}
              >
                {config.agreeTerms.btnLabel.agree}
              </button>
              <button
                className="btn btn--secondary"
                role="button"
                onClick={() => closeTermsPopup()}
              >
                {config.agreeTerms.btnLabel.close}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

const mapStateToProps = ({ testDriveReducer, preBookingReducer }) => {
  return {
    nearByVidaCentreList: testDriveReducer.nearByVidaCentreList,
    selectedPinCode: preBookingReducer.pincode,
    prebookingState: preBookingReducer.state,
    prebookingCity: preBookingReducer.city,
    branchId: preBookingReducer.branchId
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setPreBookingUserInfo: (data) => {
      dispatch(setPreBookingUserDataAction(data));
    }
  };
};

QuickPurchaseForm.propTypes = {
  config: PropTypes.any,
  cityList: PropTypes.array,
  handleCityDropdownChange: PropTypes.func,
  handleFormSubmit: PropTypes.func,
  userData: PropTypes.object,
  updateOverridePrice: PropTypes.func,
  nearByVidaCentreList: PropTypes.array,
  selectedPinCode: PropTypes.string,
  prebookingState: PropTypes.string,
  prebookingCity: PropTypes.string,
  branchId: PropTypes.string,
  showRegisterError: PropTypes.string,
  setSelectedDealerData: PropTypes.func,
  isPurchaseButtonDisabled: PropTypes.bool
};
export default connect(mapStateToProps, mapDispatchToProps)(QuickPurchaseForm);
