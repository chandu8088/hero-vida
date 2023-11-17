import appUtils from "./appUtils";
import loginUtils from "./loginUtils";

function analyticsUtils() {
  const metaObject = {};
  let pageInfo = {},
    userInfo = {},
    errorConfig = {};

  /**
   * Method to set Userinfo on every event
   */
  const getUserInfo = () => {
    if (loginUtils.isSessionActive()) {
      return {
        status: "logged-in",
        ...loginUtils.getVidaID()
      };
    } else {
      return {
        status: "logged-out"
      };
    }
  };

  const utils = {
    /**
     * Method to convert price datatype string to float
     */
    priceConversion(productPrice) {
      return parseFloat(productPrice.split(",").join(""));
    },

    /**
     * Method to check whether analytics is enabled or not
     */
    isAnalyticsEnabled() {
      return appUtils.getAnalyticsConfig("isAnalyticsEnabled");
    },

    /**
     * Method to initialize the analytics data from Meta tags
     */
    initAnalyticsData() {
      const metaElements = document.querySelectorAll("meta[property]");
      for (const meta of metaElements) {
        const property = meta.getAttribute("property");
        const content = meta.getAttribute("content");
        if (property != undefined) {
          metaObject[property] = content;
        }
      }

      pageInfo = {
        pageCategory: metaObject.pageCategory || "",
        pageName: metaObject.pageName || metaObject.pageCategory || "",
        journeyName: metaObject.pageCategory || "",
        language: metaObject.language || "",
        country: metaObject.country || "",
        eventName: "",
        platform: "Web"
      };

      userInfo = getUserInfo();
      errorConfig = {
        errorDescription: metaObject.errorDescription || "",
        errorType: metaObject.errorCode || "",
        errorPage: metaObject.isErrorPage
          ? metaObject.isErrorPage.toLowerCase() == "true"
            ? true
            : false
          : false
      };
    },

    /**
     * Method to get page name dynamically
     */
    getDynamicPageName(additionalPageName) {
      return additionalPageName
        ? additionalPageName === ""
          ? pageInfo.pageCategory
          : `${pageInfo.pageCategory}${additionalPageName}`
        : pageInfo.pageName || pageInfo.pageCategory;
    },

    /**
     * Method to get journey name dynamically
     */
    getDynamicJourneyName(additionalJourneyName) {
      return additionalJourneyName
        ? additionalJourneyName === ""
          ? pageInfo.pageCategory
          : `${pageInfo.pageCategory} ${additionalJourneyName}`
        : pageInfo.pageCategory;
    },

    /**
     * Method to call on every page load
     * @param {string} additionalPageName
     * @param {string} additionalJourneyName
     */
    trackPageLoad(additionalPageName, additionalJourneyName) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: !JSON.parse(errorConfig["errorPage"]) ? "pageView" : "errorPage",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(additionalPageName),
            journeyName: this.getDynamicJourneyName(additionalJourneyName),
            eventName: !JSON.parse(errorConfig["errorPage"])
              ? "pageView"
              : "errorPage"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        ...(!JSON.parse(errorConfig["errorPage"]) || errorConfig)
      });
    },

    /**
     * Method to call only for Login Page after generate otp button clicked
     */
    trackLoginPageView() {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: !JSON.parse(errorConfig["errorPage"]) ? "pageView" : "errorPage",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Login:Verify OTP",
            journeyName: "Login",
            eventName: !JSON.parse(errorConfig["errorPage"])
              ? "pageView"
              : "errorPage"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        ...(!JSON.parse(errorConfig["errorPage"]) || errorConfig)
      });
    },

    /**
     * Method to call only for Registration Page after generate otp button clicked
     */
    trackSignupPageView(additionalPageName) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: !JSON.parse(errorConfig["errorPage"]) ? "pageView" : "errorPage",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Signup:" + additionalPageName,
            journeyName: "Registration",
            eventName: !JSON.parse(errorConfig["errorPage"])
              ? "pageView"
              : "errorPage"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        ...(!JSON.parse(errorConfig["errorPage"]) || errorConfig)
      });
    },

    /**
     * Method to call only for Prebooking Page after verify otp button clicked
     */
    trackPreBookPageView(pageViewName) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: !JSON.parse(errorConfig["errorPage"]) ? "pageView" : "errorPage",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:${pageViewName}`,
            eventName: !JSON.parse(errorConfig["errorPage"])
              ? "pageView"
              : "errorPage"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        ...(!JSON.parse(errorConfig["errorPage"]) || errorConfig)
      });
    },

    /**
     * Method to call on Service not available
     * @param {object} customLink
     * @param {object} location
     * @param {object} error
     * @param {string} additionalPageName
     * @param {string} additionalJourneyName
     */
    trackServiceNotAvailable(
      customLink,
      location,
      error,
      additionalPageName,
      additionalJourneyName
    ) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "serviceNotAvailable",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(additionalPageName),
            journeyName: this.getDynamicJourneyName(additionalJourneyName),
            eventName: "serviceNotAvailable"
          }
        },
        customLink: customLink,
        location: location,
        error: error,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on CTA click
     * @param {object} customLink
     * @param {string} additionalPageName
     * @param {string} additionalJourneyName
     * @param {function} redirectCallback
     */
    trackCtaClick(
      customLink,
      additionalPageName,
      additionalJourneyName,
      redirectCallback
    ) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(additionalPageName),
            journeyName: this.getDynamicJourneyName(additionalJourneyName),
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on Register now click in login page
     * @param {object} customLink
     */
    trackRegisterNow(customLink) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Login",
            journeyName: "Login",
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on Login link in Signup page
     * @param {object} customLink
     */
    trackLoginNow(customLink) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Registration",
            journeyName: "Registration",
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call after verify otp clicked in login page
     * @param {object} customLink
     */
    trackLoginOTPClick(customLink) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Login:Verify OTP",
            journeyName: "Login",
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call after verify otp clicked in singup page
     * @param {object} customLink
     */
    trackSignupOTPClick(customLink) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Registration",
            journeyName: "Registration",
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call after Change Number clicked in Pre-Booking pre login
     * @param {object} customLink
     */
    trackPreBookChangeNumber(customLink) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:Mobile Number`,
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call after Agree link clicked in registration page
     * @param {object} customLink
     */
    trackTermsCondition(customLink, redirectCallback) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Registration",
            journeyName: "Registration",
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on every social icon on the page
     * @param {object} customLink
     * @param {function} redirectCallback
     */
    trackSocialIcons(customLink, redirectCallback) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on Login Start
     */
    trackLoginStart() {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "loginStart",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Login",
            journeyName: "Login",
            eventName: "loginStart"
          }
        },
        user: {
          userInfo: {
            status: userInfo.status
          }
        }
      });
    },

    /**
     * Method to call on Login Complete
     * @param {function} redirectCallback
     */
    trackLoginComplete(redirectCallback) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "loginComplete",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Login:Verify OTP",
            journeyName: "Login",
            eventName: "loginComplete"
          }
        },
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on Signup Start
     */
    trackSignupStart() {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "signupStart",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Signup",
            journeyName: "Registration",
            eventName: "signupStart"
          }
        },
        user: {
          userInfo: {
            status: userInfo.status
          }
        }
      });
    },

    /**
     * Method to call on Signup Complete
     * @param {string} redirectCallback
     */
    trackSignupComplete(redirectCallback) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "signupComplete",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Signup:Verify OTP",
            journeyName: "Registration",
            eventName: "signupComplete"
          }
        },
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on update profile
     * @param {object} profileDetails
     */
    trackUpdateProfile(location) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "updateProfile",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Profile",
            journeyName: "Profile",
            eventName: "updateProfile"
          }
        },
        location: location,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on Test drive booking start
     */
    trackTestDriveBookingStart() {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "testDriveBookingStart",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:Location Selection`,
            journeyName: `${pageInfo.pageCategory} Booking`,
            eventName: "testDriveBookingStart"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        bookingDetails: {
          bookingStatus: `${pageInfo.pageCategory} Start`
        }
      });
    },

    /**
     * Method to call on custom button click event
     * @param {object} customLink
     * @param {object} location
     * @param {object} productDetails
     * @param {string} additionalPageName
     * @param {string} additionalJourneyName
     * @param {url} redirectCallback
     */
    trackCustomButtonClick(
      customLink,
      location,
      productDetails,
      additionalPageName,
      additionalJourneyName,
      redirectCallback
    ) {
      window.adobeDataLayer.push({
        event: "customButtonClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(additionalPageName),
            journeyName: this.getDynamicJourneyName(additionalJourneyName),
            eventName: "customButtonClick"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        customLink: customLink,
        location: location,
        productDetails: productDetails
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call page load on shortterm/longterm
     * @param {string} additionalPageName
     * @param {string} testDriveType
     */
    trackTestdrivePageLoad(additionalPageName, testDriveType) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "pageView",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}${additionalPageName}`,
            journeyName: `${pageInfo.pageCategory} Booking`,
            eventName: "pageView"
          }
        },
        bookingDetails: {
          testDriveType: testDriveType
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on custom button click event
     * @param {object} customLink
     * @param {object} location
     * @param {object} productDetails
     * @param {string} additionalPageName
     * @param {string} additionalJourneyName
     * @param {url} redirectCallback
     */
    trackNotificationCBClick(
      customLink,
      location,
      productDetails,
      bookingDetails,
      additionalPageName,
      additionalJourneyName,
      redirectCallback
    ) {
      window.adobeDataLayer.push({
        event: "customButtonClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(additionalPageName),
            journeyName: this.getDynamicJourneyName(additionalJourneyName),
            eventName: "customButtonClick"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        customLink: customLink,
        location: location,
        productDetails: productDetails,
        bookingDetails: bookingDetails
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on Test drive booking complete
     * @param {object} location
     * @param {object} productDetails
     * @param {object} bookingDetails
     * @param {object} order
     */
    trackTestDriveComplete(location, productDetails, bookingDetails, order) {
      window.adobeDataLayer.push({
        event: "testDriveBookingComplete",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:Booking Confirmation`,
            journeyName: `${pageInfo.pageCategory} Booking`,
            eventName: "testDriveBookingComplete"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        location: location,
        productDetails: productDetails,
        bookingDetails: bookingDetails,
        order: order
      });
    },

    /**
     * Method to call on Test drive cancel booking
     * @param {object} location
     * @param {object} productDetails
     * @param {object} bookingDetails
     * @param {object} cancellation
     */
    trackTestRideCancel(
      location,
      productDetails,
      bookingDetails,
      cancellation
    ) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "testRideCancelBooking",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `Test Drive:Cancellation`,
            journeyName: `Test Drive Cancellation`,
            eventName: "testRideCancelBooking"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        location: location,
        productDetails: productDetails,
        bookingDetails: bookingDetails,
        cancellation: cancellation
      });
    },

    /**
     * Method to call on Test drive Reschedule
     * @param {object} location
     * @param {object} productDetails
     * @param {object} bookingDetails
     */
    trackTestRideReschedule(
      location,
      productDetails,
      bookingDetails,
      redirectCallback
    ) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "testRideReschedule",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:Reschedule Confimation`,
            journeyName: `${pageInfo.pageCategory} Reschedule`,
            eventName: "testRideReschedule"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        location: location,
        productDetails: productDetails,
        bookingDetails: bookingDetails
      });

      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on Pre booking start
     * @param {string} additionalPageName
     * @param {object} configuratorDetails
     */
    trackPreBookingStart(additionalPageName, configuratorDetails) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "preBookingStart",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}${additionalPageName}`,
            eventName: "preBookingStart"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        bookingDetails: {
          bookingStatus: `${pageInfo.pageCategory} Start`
        },
        configuratorDetails: configuratorDetails
      });
    },

    /**
     * Method to call on Pre booking Continue Button Click
     * @param {object} customLink
     * @param {object} location
     * @param {object} productDetails
     * @param {object} configuratorDetails
     */
    trackPreBookingPayment(
      customLink,
      location,
      productDetails,
      configuratorDetails
    ) {
      window.adobeDataLayer.push({
        event: "preBookingPaymentinitiated",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:Booking Summary`,
            eventName: "preBookingPaymentinitiated"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        customLink: customLink,
        location: location,
        productDetails: productDetails,
        configuratorDetails: configuratorDetails
      });
    },

    /**
     * Method to call on Pre booking Complete
     * @param {object} location
     * @param {object} productDetails
     * @param {object} bookingDetails
     * @param {object} order
     * @param {object} configuratorDetails
     */
    trackPreBookingComplete(
      location,
      productDetails,
      bookingDetails,
      order,
      configuratorDetails
    ) {
      window.adobeDataLayer.push({
        event: "preBookingComplete",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}:Confirmation`,
            eventName: "preBookingComplete"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        location: location,
        productDetails: productDetails,
        bookingDetails: bookingDetails,
        order: order,
        configuratorDetails: configuratorDetails
      });
    },

    /**
     * Method to call on Pre booking Complete
     * @param {object} productDetails
     * @param {object} bookingDetails
     * @param {object} order
     * @param {object} configuratorDetails
     */
    trackPreBookingCancel(
      productDetails,
      bookingDetails,
      cancellationDetail,
      configuratorDetails
    ) {
      window.adobeDataLayer.push({
        event: "preBookingCancel",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `Pre-Booking:Cancellation`,
            journeyName: `Pre-Booking Cancellation`,
            eventName: "preBookingCancel"
          }
        },
        user: {
          userInfo: getUserInfo()
        },
        productDetails: productDetails,
        bookingDetails: bookingDetails,
        cancellationDetail: cancellationDetail,
        configuratorDetails: configuratorDetails
      });
    },
    /**
     * Method to call Contactus
     * @param {string} journeyName
     * @param {string} eventName
     * @param {object} customLink
     */
    trackContactus(journeyName, eventName, customLink) {
      window.adobeDataLayer.push({
        event: eventName,
        page: {
          pageInfo: {
            ...pageInfo,
            journeyName: journeyName,
            eventName: eventName
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call Booking start for purchase configurator page
     * @param {object} productDetails
     * @param {object} location
     * @param {object} configuratorDetails
     */
    trackBookingStart(productDetails, location, configuratorDetails) {
      window.adobeDataLayer.push({
        event: "bookingStart",
        page: {
          pageInfo: {
            ...pageInfo,
            eventName: "bookingStart"
          }
        },
        productDetails: productDetails,
        location: location,
        configuratorDetails: configuratorDetails,
        bookingDetails: {
          bookingStatus: "Booking Start"
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call Booking Checkout triggered
     * @param {object} customLink
     * @param {object} location
     * @param {object} productDetails
     *  * @param {object} bookingDetails
     * @param {object} priceBreakup
     * @param {object} configuratorDetails
     */
    trackBookingCheckout(
      location,
      productDetails,
      bookingDetails,
      priceBreakup,
      configuratorDetails,
      customLink,
      redirectCallback
    ) {
      window.adobeDataLayer.push({
        event: "bookingCheckout",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Booking:Details",
            eventName: "bookingCheckout"
          }
        },
        customLink: customLink,
        location: location,
        productDetails: productDetails,
        bookingDetails: bookingDetails,
        priceBreakup: priceBreakup,
        configuratorDetails: configuratorDetails,
        user: {
          userInfo: getUserInfo()
        }
      });

      redirectCallback && redirectCallback();
    },

    /**
     * Method to call Booking payment initiated
     * @param {object} customLink
     * @param {object} location
     * @param {object} productDetails
     */
    trackBookingPayment(customLink, location, productDetails) {
      window.adobeDataLayer.push({
        event: "bookingPaymentInitiated",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Booking:Billing Details",
            eventName: "bookingPaymentInitiated"
          }
        },
        customLink: customLink,
        location: location,
        productDetails: productDetails,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call Booking complete
     * @param {object} location
     * @param {object} productDetails
     * @param {object} insuranceDetails
     * @param {object} bookingDetails
     * @param {object} order
     * @param {object} priceBreakup
     *  @param {object} configuratorDetails
     */
    trackBookingComplete(
      location,
      productDetails,
      insuranceDetails,
      bookingDetails,
      order,
      priceBreakup,
      configuratorDetails
    ) {
      window.adobeDataLayer.push({
        event: "bookingComplete",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Booking:Completed",
            eventName: "bookingComplete"
          }
        },
        location: location,
        productDetails: productDetails,
        insuranceDetails: insuranceDetails,
        bookingDetails: bookingDetails,
        order: order,
        priceBreakup: priceBreakup,
        configuratorDetails: configuratorDetails,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on filling nominee details
     */
    trackNominee(redirectCallback) {
      window.adobeDataLayer.push({
        event: "nomineeDetailUpdated",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Booking:Nominee details",
            eventName: "nomineeDetailUpdated"
          }
        },
        bookingDetails: {
          bookingStatus: "Nominee Details Updated"
        },
        customLink: {
          name: "Submit",
          position: "Bottom",
          type: "Button",
          clickType: "other"
        },
        user: {
          userInfo: getUserInfo()
        }
      });

      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on upload documents
     * @param {object} documentDetails
     * @param {function} redirectCallback
     *
     */
    trackDocument(documentDetails, redirectCallback) {
      window.adobeDataLayer.push({
        event: "documentUploadCompleted",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Booking:Upload Documents",
            eventName: "documentUploadCompleted"
          }
        },
        bookingDetails: {
          bookingStatus: "Documents Uploaded Successfully"
        },
        customLink: {
          name: "Submit",
          position: "Bottom",
          type: "Button",
          clickType: "other"
        },
        documentDetails: documentDetails,
        user: {
          userInfo: getUserInfo()
        }
      });

      redirectCallback && redirectCallback();
    },

    /**
     * Method to call aadhar card status
     * @param {string} additionalPageName
     * @param {string} bookingStatus
     *
     */
    trackAadharCard(additionalPageName, bookingStatus) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "aadharCardVerification",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(additionalPageName),
            eventName: "aadharCardVerification"
          }
        },
        bookingDetails: {
          bookingStatus: bookingStatus
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call hero sure registration start
     */
    trackExchangeStart() {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "exchangeTwoWheelerStart",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Exchange Two-Wheeler:Start",
            journeyName: "Exchange Two-Wheeler",
            eventName: "exchangeTwoWheelerStart"
          }
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call hero sure page load
     */
    trackExchangePageLoad(additionalPageName) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "pageView",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `Exchange Two-Wheeler${additionalPageName}`,
            journeyName: "Exchange Two-Wheeler",
            eventName: "pageView"
          }
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call hero sure registration completed
     */
    trackExchangeCompleted() {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "exchangeTwoWheelerComplete",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Exchange Two-Wheeler:Completed",
            journeyName: "Exchange Two-Wheeler",
            eventName: "exchangeTwoWheelerComplete"
          }
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on Register now click in login page
     * @param {object} customLink
     * @param {string} additionalPageName
     */
    trackHeroSureCTAEvent(customLink, additionalPageName) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "ctaClick",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: "Exchange Two-Wheeler" + additionalPageName,
            journeyName: "Exchange Two-Wheeler",
            eventName: "ctaClick"
          }
        },
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on Quick reserve initiated
     * @param {object} location
     * @param {object} productDetails
     * @param {object} customLink
     * @param {function} redirectCallback
     */
    trackQuickReserveInit(
      location,
      productDetails,
      customLink,
      redirectCallback
    ) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "quickReserveInitiated",
        page: {
          pageInfo: {
            ...pageInfo,
            journeyName: "Quick Reserve",
            eventName: "quickReserveInitiated"
          }
        },
        location: location,
        productDetails: productDetails,
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },
    /**
     * Method to call page load on aadhar verification
     * @param {string} additionalPageName
     * @param {string} aadharVerified
     */
    trackAadharVerificationPageLoad(additionalPageName, aadharVerified) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "pageView",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: `${pageInfo.pageCategory}${additionalPageName}`,
            journeyName: `Booking`,
            eventName: "pageView"
          }
        },
        bookingDetails: {
          aadharVerified: aadharVerified
        },
        user: {
          userInfo: getUserInfo()
        }
      });
    },

    /**
     * Method to call on Quick reserve initiated
     * @param {object} location
     * @param {object} productDetails
     * @param {object} customLink
     * @param {function} redirectCallback
     */
    trackQuickPurchaseInit(
      location,
      productDetails,
      customLink,
      redirectCallback
    ) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "quickPurchaseInitiated",
        page: {
          pageInfo: {
            ...pageInfo,
            journeyName: "Quick Pruchase",
            eventName: "quickPurchaseInitiated"
          }
        },
        location: location,
        productDetails: productDetails,
        customLink: customLink,
        user: {
          userInfo: getUserInfo()
        }
      });
      redirectCallback && redirectCallback();
    },

    /**
     * Method to call on page scroll
     * @param {number} scrollPercentage
     */
    trackPageScroll(scrollPercentage) {
      window.adobeDataLayer = window.adobeDataLayer || [];
      window.adobeDataLayer.push({
        event: "scrollTracking",
        page: {
          pageInfo: {
            ...pageInfo,
            pageName: this.getDynamicPageName(""),
            journeyName: this.getDynamicJourneyName(""),
            eventName: !JSON.parse(errorConfig["errorPage"])
              ? "pageView"
              : "errorPage"
          }
        },
        scrollPercentage,
        user: {
          userInfo: getUserInfo()
        },
        ...(!JSON.parse(errorConfig["errorPage"]) || errorConfig)
      });
    }
  };

  return utils;
}

const utils = analyticsUtils();
export default Object.freeze(utils);
