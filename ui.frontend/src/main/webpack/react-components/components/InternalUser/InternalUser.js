import React from "react";
import PropTypes from "prop-types";
import ComplainForm from "./CompainForm/CompainForm";

const InternalForm = (props) => {
  const { bookingForm } = props.config;

  return (
    <div className="vida-internal-user__container">
      <div className="vida-internal-user__content">
        <ComplainForm config={bookingForm} />
      </div>
    </div>
  );
};

InternalForm.propTypes = {
  config: PropTypes.shape({
    bookingForm: PropTypes.object
  })
};

InternalForm.defaultProps = {
  config: {}
};

export default InternalForm;
