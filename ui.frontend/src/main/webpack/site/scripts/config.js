/* Fallback and additional configs */
const config = {
  globalConfig: {
    magentoAPIUrl:
      "https://integration2-hohc4oi-b5gpcg435vm6u.ap-3.magentosite.cloud/graphql",
    defaultCountryCode: "+91",
    googleAPIKey: "AIzaSyDSC3k4yvBHeWO4JMeavHs_tEYlISHfBX0",
    YoutubeAPIKey: "AIzaSyCMpX0hLAL_vGDNYWb-2fh1pM3lpturV-A",
    cryptoPublicKey: "",
    RSAPublicKey:
      "-----BEGIN PUBLIC KEY-----\
MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAwLyogq0vFmwIKDoNkkgd\
upvMsmtl1PwevrLOT/3V6hH9P9qjpcuvwqzaIyP4J4lgKZyaYjIZ83LqOGLFYhFs\
8tX2oxMxiUZZefCPSSFcvVEyl4Tef7ETf9wCGkMne+/gjM9gVUGQMKgYPsVTxnXx\
8M3Z8QYZMyp+3jfA3iR13PsDySefjxp4YvM7wgt3v9U1kv3Ke0vXtQCtVaScQ/jv\
FzISoQyr6zzL0EzirK1+t5I5VR9qb1umDVW/mO35UycjSV4U3lmF2ExNcT5wtchL\
nhMQLo2lPknegfPcP7RoLsm3kXNzxheK5CxMxHnM7xjsiRpn81hfoB3hdHx4Q2Df\
h37pArOQWWk3fLQbvXEhunfiL4VZNva5+18NBDtwG87+1t1AZWPDqv9ai/0ZtHc1\
EZxVDOvmKbZgX04zEiQZI3DO0H3Cn0j6myRLBD2WPFvU0UmO/iMSQwiFZseyiAdX\
lEusJmr486cswqPzahU37Yj3ndV3Yuw3OAShHN6mOAfdS+06ieen5kUocc/hOdGF\
lzVMW0b8cTHIiZWHyEaOlwL9ZgXkUvY4oNCALGo6c2vKyUKuBRjFNyu/xxkIIqys\
TBBN8Ivrpo+0qIfry5GZT0bJ/ubgBVUC5jShrHMRZVd5meFEpNZLyGp1dSJW+Duw\
cX/hc6bvyL/AfwjTQ8SzwWkCAwEAAQ==\
-----END PUBLIC KEY-----",
    freedoBookingLimit: 10,
    currency: "INR",
    currencyCountry: "en-IN",
    currencySymbol: "₹",
    phNumberStartsWith: ["9", "8", "7", "6"],
    countryList: [
      {
        label: "Select Country",
        value: ""
      },
      {
        label: "India",
        value: "India"
      }
    ],
    dropdownInitialValue: [
      {
        label: "Select",
        value: ""
      }
    ],
    stateList: [
      {
        label: "Select State",
        value: ""
      }
    ],
    cityList: [
      {
        label: "Select City",
        value: ""
      }
    ],
    citySearchList: [
      {
        label: "Select / Search Your City",
        value: ""
      }
    ],
    stateSearchList: [
      {
        label: "Select / Search Your State",
        value: ""
      }
    ],
    countryCodes: [
      {
        label: "+91",
        value: "+91"
      }
    ],
    modelDefaultOption: [
      {
        label: "Select Model",
        value: ""
      }
    ],
    branchDefaultOption: [
      {
        label: "Select Centre",
        value: ""
      }
    ],
    rentDurationDefaultOption: [
      {
        label: "Select rent duration",
        value: ""
      }
    ],
    datesDefaultOption: [
      {
        label: "Select date",
        value: ""
      }
    ],
    timeDefaultOption: [
      {
        label: "Select Time",
        value: ""
      }
    ],
    productColorCodes: {
      Blue: "linear-gradient(180deg, #00A1E7 0%, #0F78A6 65.1%, #00A1E7 100%)",
      Red: "linear-gradient(180deg, #FF0000 0%, #B51818 67.71%, #FF0000 100%)",
      "Matt Grey":
        "linear-gradient(180deg, #5B5B5B 0%, #7D7D7D 64.06%, #5B5B5B 100%)",
      "Metallic Silver":
        "linear-gradient(180deg, #FFFFFF 0%, #D9D9D9 63.54%, #FFFFFF 100%)",
      Grey: "#808080",
      White: "#fff",
      Black: "#000",
      Orange: "#fd7343"
    },
    dropDown: {
      minSearchCharacter: 1,
      noResultMessage: "No result found",
      debounceTime: 100
    }
  },
  pageConfig: {
    showLeavePageNotification: "false"
  }
};

export default Object.freeze(config);
