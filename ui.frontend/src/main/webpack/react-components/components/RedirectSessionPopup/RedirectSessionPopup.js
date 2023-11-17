import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import Popup from "../Popup/Popup";

const RedirectSessionPopup = (props) => {
  const { config } = props;
  const [showSessionExpiryPopup, setShowSessionExpiryPopup] = useState(true);
  const [titleMessage, setTitleMessage] = useState(config.sessionExpiryTitle);

  useEffect(() => {
    if (config.isSessionExpired) {
      document.querySelector(".vida-session-popup").style = "display: block";
      setTitleMessage(config.logoutTitle);
    } else {
      document.querySelector(".vida-session-popup").style = "display: none";
    }
  }, []);

  const handleLogin = () => {
    window.location.href = config.primarybtn.btnLink;
  };

  const handleHomepage = () => {
    window.location.href = config.secondarybtn.btnLink;
  };

  return (
    <div className="vida-session-popup">
      {showSessionExpiryPopup && (
        <Popup mode="small" handlePopupClose={() => handleHomepage()}>
          <div className="vida-session-popup--text">
            <h3> {titleMessage}</h3>
          </div>
          <div className="vida-session-popup--buttons">
            <button onClick={handleLogin} className="btn btn--primary">
              {config.primarybtn.btnName}
            </button>
            <button onClick={handleHomepage} className="btn btn--secondary">
              {config.secondarybtn.btnName}
            </button>
          </div>
        </Popup>
      )}
    </div>
  );
};

RedirectSessionPopup.propTypes = {
  config: PropTypes.object
};

export default RedirectSessionPopup;
