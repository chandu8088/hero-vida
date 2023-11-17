import API from "../../../services/rest.service";

function getStoreDetails(url) {
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

export { getStoreDetails };
