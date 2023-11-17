import API from "../../../services/rest.service";
import appUtils from "../../../site/scripts/utils/appUtils";

export function getAddressTypesData() {
  const url = appUtils.getAPIUrl("addressTypesUrl");
  if (url) {
    return new Promise((resolve, reject) => {
      API.getData(url)
        .then((response) => {
          if (response) {
            resolve(response.data);
          }
        })
        .catch((error) => {
          reject(error);
        });
    });
  }
}
