import {
  USER_FORM_DATA,
  USER_CHECK,
  SFID,
  USER_STATUS
} from "./userAccessTypes";

const initialState = {
  countryCode: "",
  customerCity: "",
  customerState: "",
  customerCountry: "",
  numberOrEmail: "",
  mobileNumber: "",
  fname: "",
  lname: "",
  email: "",
  isLogin: false, // only for Login/Register API calls
  sfid: "",
  customerExists: false,
  isUserLoggedIn: false,
  whatsappConsent: true
};

// TODO: Login & Register/ Test drive uses same reducer with different action type - requires cleanup

/* User Access Reducer */
export default function userAccessReducer(state = initialState, action) {
  switch (action.type) {
    case USER_FORM_DATA:
      return {
        ...state,
        countryCode: action.payload.countryCode,
        customerCity: action.payload.customer_city,
        customerState: action.payload.customer_state,
        customerCountry: action.payload.customer_country,
        numberOrEmail: action.payload.numberOrEmail,
        mobileNumber: action.payload.mobileNumber,
        fname: action.payload.fname,
        lname: action.payload.lname,
        email: action.payload.email,
        whatsappConsent: action.payload.whatsappConsent
      };
    case USER_CHECK:
      return {
        ...state,
        isLogin: action.payload.isLogin,
        customerExists: action.payload.customerExists
      };
    case SFID:
      return {
        ...state,
        sfid: action.payload.SFID
      };
    case USER_STATUS:
      return {
        ...state,
        isUserLoggedIn: action.payload.isUserLoggedIn
      };
    default:
      return state;
  }
}
