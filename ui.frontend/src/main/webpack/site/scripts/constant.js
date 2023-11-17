const CONSTANT = {
  CUSTOM_COMPONENT_TYPE: "react",
  STATIC_COMPONENTS_PATH: "components",
  REACT_COMPONENTS_PATH: "react-components/components",
  NOTIFICATION_TYPES: {
    SUCCESS: "success",
    ERROR: "error"
  },
  CUSTOMER_TYPES: {
    INDIVIDUAL: "Individual",
    CORPORATE: "Corporate"
  },
  HTTP_ERROR_CODES: {
    400: "Bad Request, please try again later.",
    404: "Resource not found.",
    500: "Internal Server Error, please try again later.",
    503: "Server is busy, please try again later.",
    504: "Gateway timeout, please try again later."
  },
  OTHER_HTTP_ERROR: "Network Error, please try again later.",
  COOKIE_SESSION_TOKEN: "SESSION_TOKEN",
  COOKIE_ACCOUNT_ID: "ACCOUNT_ID",
  COOKIE_LEAD_ID: "LEAD_ID",
  COOKIE_CUSTOMER_NUMBER: "CUS_NUM",
  COOKIE_PIN_NUMBER: "PIN_NUM",
  COOKIE_OPPORTUNITY_ID: "OPPORTUNITY_ID",
  COOKIE_ADOBE_ANALYTICS_DATA: "ADOBE_ANALYTICS_DATA",
  CAPTCHA_ENABLED: false,
  EMAIL_REGEX:
    /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
  NAME_REGEX: /^(?!.*'')[A-Za-z' ]+$/,
  NUMBER_REGEX: /^[1-9][0-9]+$/,
  AADHAAR_REGEX: /^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$/,
  FILE_REGEX_DOC: /[\/.](jpg|jpeg|png|pdf)$/,
  FILE_REGEX_IMAGE: /[\/.](jpg|jpeg|png)$/,
  RESTRICT_AGE: 2,
  RESTRICT_PINCODE: 6,
  PIN_CODE_REGEX: /^[1-9][0-9]{5}$/,
  LONG_TERM_DATE_SELECTION_LIMIT: 15,
  LONG_TERM_MODE_OF_PICKUP: {
    HOME_DELIVERY: 1,
    SELF_PICKUP: 2
  },
  TEST_DRIVE: {
    LOCATION_TYPE: {
      CENTRE: "centre",
      HOME: "home"
    }
  },
  TEST_RIDE_OPTIONS: {
    SHORT_TERM: "SHORT_TERM",
    LONG_TERM: "LONG_TERM"
  },
  TEST_RIDE_STATUS: {
    SCHEDULED: "Scheduled",
    CONFIRMED: "Confirmed",
    START_TEST_RIDE: "Start Test Ride",
    END_TEST_RIDE: "End Test Ride",
    CANCELLED: "Cancelled",
    LAPSED: "Lapsed",
    COMPLETED: "Completed",
    PAYMENT_PENDING: "Payment Pending",
    UPCOMING: "Upcoming",
    ONGOING: "Ongoing",
    ENDED: "Ended"
  },
  PRE_BOOKING_STEPS: {
    INITIAL_STEP: 1,
    LOGGEDIN_USER_STEP: 2,
    TOTAL_STEPS: 3
  },
  TRANSACTION_TYPE: {
    PRODUCT_DEMO: "Product Demo",
    PRODUCT_DEMO_AT_SITE: "Product Demo At Site",
    PRODUCT_DELIVERY: "Delivery",
    PRODUCT_DELIVERY_AT_SITE: "Delivery At Site"
  },
  PAYMENT_METHOD: {
    FULL_PAYMENT: "fullPayment",
    PARTIAL_PAYMENT: "partialPayment",
    LOAN: "loan",
    LEASE: "lease"
  },
  PAYMENT_MODE: {
    ONLINE: "online",
    PARTIAL: "partial",
    CASH: "cash",
    LOAN: "loan",
    LEASE: "lease"
  },
  PARTIAL_PAYMENT_TYPE: {
    FULL: "full",
    MINIMUM: "minimum",
    OTHER: "other"
  },
  PAYMENT_TYPE: {
    FAMESUBSIDY: "FAMESUBSIDY",
    EXCHANGE: "EXCHANGE",
    DOWNPAYMENT: "DOWNPAYMENT"
  },
  CENTER: {
    DEFAULT: "default",
    EXPERIENCE_CENTER: "experienceCenter",
    SERVICE_CENTER: "serviceCenter",
    CHARGING_STATION: "chargingStations",
    SWAPPING_STATION: "swappingStations"
  },
  PURCHASE_STATUS: {
    DRAFT: "Draft",
    BOOKED: "Booked",
    CV: "Credit Verified",
    FULFILMENT: "Fulfilment",
    KYC_DONE: "KYC Done",
    INVOICING: "Invoicing",
    DELIVERY: "Delivery",
    CLOSED: "Closed",
    CANCELLED: "Cancelled"
  },
  CURRENT_STATUS: {
    PREBOOKED: 0,
    PURCHASE_PENDING: 1,
    SCOOTER_PURCHASED: 2,
    SCOOTER_RESERVED: 3,
    SCOOTER_DELIVEREY: 4
  },
  TRACKER_STATUS: {
    DRAFT: "Draft",
    REGISTRATION: "Registration",
    INSURANCE: "Insurance",
    HSRP: "HSRP",
    PDI: "PDI",
    DELIVERED: "Delivered"
  },
  ORDERS_VIEW: {
    PREBOOKING: "Prebooking",
    BOOKING: "Booking"
  },
  PURCHASE_VIEW: {
    PREBOOKING: "Prebooking",
    BOOKING: "Booking",
    OUTSTANDING: "Outstanding"
  },
  PREBOOKING_STATUS: {
    CONFIRMED: "Prebooking Confirmed",
    CANCELLED: "Prebooking Cancelled"
  },
  PAYMENT_STATUS: {
    SUCCESS: "success",
    SUCCESS_PENDING: "success_pending",
    PAYMENT_PENDING: "payment_pending",
    FAILURE: "failure",
    ABORTED: "aborted"
  },
  REDIRECTION_STATUS: {
    QUICK_RESERVE: "quick_reserve"
  },
  SUBSCRIPTION_ITEM_CODE: {
    BASE_ITEM_CODE: "BASE002",
    UMBRELLA_ITEM_CODE: "UMBRELLA002"
  },
  CENTERLIST: {
    EXPERIENCE_CENTER: "Experience Center",
    POP_UP_STORES: "Pop-up Stores"
  },
  LOANSTATUS: {
    INITIATED: "initiated",
    SANCTIONED: "sanctioned",
    DOWNPAYMENT_MADE: "downpayment_made",
    APPLICATION_CANCELLED: "application_cancelled",
    INVOICE_GENERATED: "invoice_generated",
    DISBURSAL_REQUESTED: "disbursal_requested",
    APPLICATION_DISBURSAL_PROCESSED: "application_disbursal_processed",
    APPLICATION_CREATED: "application_created",
    DOCUMENTS_APPROVAL_PENDING: "documents_approval_pending"
  },
  LEASESTATUS: {
    COMPLETED: "completed"
  },
  URLPARAMS: {
    ECCENTRIC: "eccentric"
  },
  GRAPHQL_EXTENSION: {
    AUTHORIZATION: "graphql-authorization"
  },
  CANCELLATION_TYPE: {
    PREBOOKING: "PREBOOKING",
    SALESORDER: "SALESORDER"
  }
};

//  Freezing the enum
Object.freeze(CONSTANT);

export default CONSTANT;
