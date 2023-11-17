import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import appUtils from "../../../site/scripts/utils/appUtils";
import analyticsUtils from "../../../site/scripts/utils/analyticsUtils";
import CONSTANT from "../../../site/scripts/constant";
import Location from "../../../site/scripts/location";
import { cryptoUtils } from "../../../site/scripts/utils/encryptDecryptUtils";
import { setSpinnerActionDispatcher } from "../../store/spinner/spinnerActions";
import { useOptimizedGetOrderData } from "../../hooks/purchaseConfig/purchaseConfigHooks";
import { useBookingPartialPaymentInfo } from "../../hooks/payment/paymentHooks";
import currencyUtils from "../../../site/scripts/utils/currencyUtils";

const PaymentDetails = (props) => {
  const { config, productDataDetails, billingAddresses } = props;

  const {
    paymentInputField,
    submitBtn,
    helpTextQuestion,
    helpTextChat,
    partialInfoPrimary,
    partialInfoSecondary,
    inputBoxErrors,
    extimatedDeliveryTime
  } = config;

  const PARTIAL = "PARTIAL";
  const isAnalyticsEnabled = analyticsUtils.isAnalyticsEnabled();
  const [totalAmount, setTotalAmount] = useState();
  const [minAmount, setMinAmount] = useState(0);
  const [partialAmount, setPartialAmount] = useState("");
  const [validationError, setValidationError] = useState();
  const queryString = window.location.href.split("?")[1];
  const decryptedParams = queryString && cryptoUtils.decrypt(queryString);
  const params = new URLSearchParams("?" + decryptedParams);
  const [orderId, setOrderId] = useState();
  const bookingPartialPaymentInfo = useBookingPartialPaymentInfo();
  const profileUrl = appUtils.getPageUrl("profileUrl");
  const locationHandler = async () => {
    const locationObj = new Location();
    await locationObj.getVidaCentreList(billingAddresses.city);
  };

  useEffect(() => {
    if (billingAddresses.city) {
      locationHandler();
    }
  }, [billingAddresses]);

  const handleChatBotEvent = (event) => {
    event.preventDefault();
    bootstrapChat && bootstrapChat();
    if (isAnalyticsEnabled) {
      const customLink = {
        name: event.currentTarget.innerText,
        position: "Bottom",
        type: "Link",
        clickType: "other"
      };
      analyticsUtils.trackCtaClick(customLink);
    }
  };

  const submitPartialPayment = async () => {
    if (!partialAmount) {
      const error = inputBoxErrors.minimum.replace("MINIMUMAMOUNT", minAmount);
      setValidationError(error);
    } else if (partialAmount < minAmount && totalAmount > minAmount) {
      const error = inputBoxErrors.minimum.replace("MINIMUMAMOUNT", minAmount);
      setValidationError(error);
    } else if (
      totalAmount < minAmount &&
      partialAmount != Math.floor(totalAmount)
    ) {
      const error = inputBoxErrors.equals.replace(
        "TOTALAMOUNT",
        Math.floor(totalAmount)
      );
      setValidationError(error);
    } else if (partialAmount > Math.floor(totalAmount)) {
      const error = inputBoxErrors.maximum.replace(
        "MAXAMOUNT",
        Math.floor(totalAmount)
      );
      setValidationError(error);
    } else {
      setSpinnerActionDispatcher(true);
      const partialPaymentResult = await bookingPartialPaymentInfo({
        variables: {
          order_id: orderId,
          payment_mode: CONSTANT.PAYMENT_MODE.ONLINE,
          payment_type: PARTIAL,
          partial_amount: partialAmount
        }
      });
      if (partialPaymentResult.data.CreateSaleOrderPayment.payment_url) {
        window.location.href =
          partialPaymentResult.data.CreateSaleOrderPayment.payment_url;
      }
    }
  };

  const getOrderData = useOptimizedGetOrderData();
  useEffect(() => {
    if (queryString) {
      if (params && params.get("orderId") && params.get("opportunityId")) {
        setSpinnerActionDispatcher(true);
        getOrderData({
          variables: {
            order_id: params.get("orderId"),
            opportunity_id: params.get("opportunityId")
          }
        }).then((res) => {
          setSpinnerActionDispatcher(true);
          if (!res.data.OpGetSaleOrderDetails.allowEdit) {
            window.location.href = profileUrl;
          } else {
            setSpinnerActionDispatcher(false);
            setTotalAmount(
              res.data.OpGetSaleOrderDetails.updated_order_grand_total
            );
            setMinAmount(res.data.OpGetSaleOrderDetails.partial_minimum_amount);
            setOrderId(res.data.OpGetSaleOrderDetails.order_increment_id);
            if (res.data.OpGetSaleOrderDetails.updated_order_grand_total) {
              // console.log(
              //   currencyUtils.getCurrencyFormatValue(
              //     Math.floor(
              //       res.data.OpGetSaleOrderDetails.updated_order_grand_total
              //     )
              //   )
              // );
              setPartialAmount(
                Math.floor(
                  res.data.OpGetSaleOrderDetails.updated_order_grand_total
                )
              );
            }
          }
        });
        // getMyScooter();
      } else {
        window.location.href = profileUrl;
      }
    } else {
      window.location.href = profileUrl;
    }
    // getUserData();
  }, []);

  return (
    <div className="vida-partial-payment bg-color--smoke-white">
      <div className="vida-container">
        <div className="vida-partial-payment__body vida-billing-pricing-new__payment">
          <div className="form__group">
            <h3>{productDataDetails.name}</h3>
            <h3>
              Total outstanding:{" "}
              {currencyUtils.getCurrencyFormatValue(Math.floor(totalAmount), 0)}
            </h3>
            <div className="vida-delivery-status-new__product-info-subtext">
              <h4>{extimatedDeliveryTime}</h4>
            </div>
            <label
              htmlFor="Partial Payment Amount"
              className="form__field-label"
            >
              {paymentInputField.label}
            </label>
            <input
              id={paymentInputField.id}
              name={paymentInputField.name}
              type="number"
              placeholder={paymentInputField.placeholder}
              className={
                "form__field-input " +
                (validationError
                  ? "vida-partial-payment__body--error-input"
                  : null)
              }
              autoComplete="off"
              value={partialAmount}
              onChange={(e) =>
                e.target.value
                  ? setPartialAmount(parseFloat(e.target.value))
                  : setPartialAmount("")
              }
            />
            <label className="vida-partial-payment__body--error">
              {validationError}
            </label>
            <label className="vida-partial-payment__body--info1">
              {partialInfoPrimary
                .replace(
                  "MAXAMOUNT",
                  currencyUtils.getCurrencyFormatValue(
                    Math.floor(totalAmount),
                    0
                  )
                )
                .replace(
                  "MINIMUMAMOUNT",
                  currencyUtils.getCurrencyFormatValue(minAmount, 0)
                )}
            </label>
            <label className="vida-partial-payment__body--info2">
              {partialInfoSecondary
                .replace(
                  "MAXAMOUNT",
                  currencyUtils.getCurrencyFormatValue(
                    Math.floor(totalAmount),
                    0
                  )
                )
                .replace(
                  "MINIMUMAMOUNT",
                  currencyUtils.getCurrencyFormatValue(minAmount, 0)
                )}
            </label>
            <div className="vida-partial-payment__aside--bottom">
              <button
                onClick={submitPartialPayment}
                className="btn btn--primary"
              >
                {submitBtn}
              </button>
              <div className="vida-delivery-status-new__help-text">
                {helpTextQuestion}
                <a href="#" onClick={(event) => handleChatBotEvent(event)}>
                  {helpTextChat}
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ purchaseConfigReducer, testDriveReducer }) => {
  return {
    billingAddresses: purchaseConfigReducer.billingAddresses,
    productDataDetails: purchaseConfigReducer.productData,
    location: testDriveReducer.nearByVidaCentreList,
    order: purchaseConfigReducer.order
  };
};

PaymentDetails.propTypes = {
  config: PropTypes.object,
  paymentInputField: PropTypes.object,
  submitBtn: PropTypes.string,
  helpTextQuestion: PropTypes.string,
  helpTextChat: PropTypes.string,
  productDataDetails: PropTypes.object,
  location: PropTypes.array,
  billingAddresses: PropTypes.object,
  partialInfoPrimary: PropTypes.string,
  partialInfoSecondary: PropTypes.string,
  inputBoxErrors: PropTypes.object,
  extimatedDeliveryTime: PropTypes.string,
  order: PropTypes.object
};

export default connect(mapStateToProps)(PaymentDetails);
