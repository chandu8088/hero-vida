import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import currencyUtils from "../../../site/scripts/utils/currencyUtils";
import ReactTooltip from "react-tooltip";

const PaymentDetails = (props) => {
  const { paymentInfo, paymentInfoData, orderStatus } = props;
  const {
    title,
    transactionIdLabel,
    purchaseLoanLabel,
    paidOnLabel,
    tooltipConfig,
    status,
    onlineTransaction,
    paidatDealer
  } = paymentInfo;

  const [showPaymentDetails, setShowPaymentDetails] = useState(true);
  const [online, setOnline] = useState([]);
  const [cash, setCash] = useState([]);
  const [paymentInformation, setPaymentInformation] = useState([]);
  const handleBreakUpDetail = () => {
    setShowPaymentDetails(!showPaymentDetails);
  };

  useEffect(() => {
    setOnline(paymentInfoData.filter((infoData) => infoData.is_online === "1"));
    setCash(paymentInfoData.filter((infoData) => infoData.is_online === ""));
    setPaymentInformation(
      paymentInfoData.sort((a, b) => a.is_online - b.is_online)
    );
  }, []);

  return (
    paymentInformation && (
      <div className="vida-payment-details">
        <div className="vida-payment-details__wrapper">
          <div className="vida-payment-details__title">
            <h3>{title}</h3>
            <i
              className={showPaymentDetails ? "icon-minus" : "icon-plus"}
              onClick={handleBreakUpDetail}
            />
          </div>
          {showPaymentDetails && cash.length > 0 ? (
            <div className="vida-payment-details__wrapper--tooltip">
              <span
                className="notification__icon"
                data-tip={tooltipConfig.info}
                data-for={tooltipConfig.id}
              >
                <i className="icon-information-circle txt-color--orange"></i>
              </span>
              <ReactTooltip
                place={tooltipConfig.infoPosition}
                type="warning"
                effect="solid"
                id={tooltipConfig.id}
              />
              <h4 className="vida-payment-details__wrapper--h4">
                {paidatDealer}
              </h4>
            </div>
          ) : (
            ""
          )}
          {showPaymentDetails &&
            cash.map((paymentInfoItem, index) => (
              <div className="vida-payment-details__info" key={index}>
                <div className="vida-payment-details__info__left">
                  {paymentInfoItem.paymentMode ? (
                    <div className="items">
                      <div className="items__label">{purchaseLoanLabel}</div>
                      <div className="items__value">
                        {paymentInfoItem.paymentMode}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                  {paymentInfoItem.payId ? (
                    <div className="items">
                      <div className="items__label">{transactionIdLabel}</div>
                      <div className="items__value">
                        {paymentInfoItem.payId}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
                <div className="vida-payment-details__info__right">
                  {paymentInfoItem.amount ? (
                    <div className="items">
                      <div className="items__value">
                        {currencyUtils.getCurrencyFormatValue(
                          paymentInfoItem.amount
                        )}
                      </div>
                      <div className="items__label">
                        {paidOnLabel}
                        {paymentInfoItem.paymentDate && (
                          <span className="items__info">on</span>
                        )}
                        {paymentInfoItem.paymentDate && (
                          <span className="items__info">
                            {paymentInfoItem.paymentDate}
                          </span>
                        )}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                  {orderStatus == "Delivery" &&
                  paymentInfoItem.gateway_status ? (
                    <div className="items">
                      <div className="items__label items__label--status">
                        {status}
                      </div>
                      <div className="items__value items__value--status">
                        {paymentInfoItem.gateway_status}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
              </div>
            ))}
          {showPaymentDetails && online.length > 0 ? (
            <div className="vida-payment-details__wrapper--tooltip">
              <h4 className="vida-payment-details__wrapper--h4">
                {onlineTransaction}
              </h4>
            </div>
          ) : (
            ""
          )}
          {showPaymentDetails &&
            online.map((paymentInfoItem, index) => (
              <div className="vida-payment-details__info" key={index}>
                <div className="vida-payment-details__info__left">
                  {paymentInfoItem.paymentMode ? (
                    <div className="items">
                      <div className="items__label">{purchaseLoanLabel}</div>
                      <div className="items__value">
                        {paymentInfoItem.paymentMode}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                  {paymentInfoItem.payId ? (
                    <div className="items">
                      <div className="items__label">{transactionIdLabel}</div>
                      <div className="items__value">
                        {paymentInfoItem.payId}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
                <div className="vida-payment-details__info__right">
                  {paymentInfoItem.amount ? (
                    <div className="items">
                      <div className="items__value">
                        {currencyUtils.getCurrencyFormatValue(
                          paymentInfoItem.amount
                        )}
                      </div>
                      <div className="items__label">
                        {paidOnLabel}
                        {paymentInfoItem.paymentDate && (
                          <span className="items__info">on</span>
                        )}
                        {paymentInfoItem.paymentDate && (
                          <span className="items__info">
                            {paymentInfoItem.paymentDate}
                          </span>
                        )}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                  {orderStatus == "Closed" && paymentInfoItem.gateway_status ? (
                    <div className="items status">
                      <div className="items__label items__label--status">
                        {status}
                      </div>
                      <div className="items__value items__value--status">
                        {paymentInfoItem.gateway_status}
                      </div>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
              </div>
            ))}
        </div>
      </div>
    )
  );
};

PaymentDetails.propTypes = {
  paymentInfo: PropTypes.shape({
    title: PropTypes.string,
    transactionIdLabel: PropTypes.string,
    purchaseLoanLabel: PropTypes.string,
    paidOnLabel: PropTypes.string,
    tooltipConfig: PropTypes.object,
    status: PropTypes.string,
    onlineTransaction: PropTypes.string,
    paidatDealer: PropTypes.string
  }),
  paymentInfoData: PropTypes.array,
  orderStatus: PropTypes.string
};

export default PaymentDetails;
