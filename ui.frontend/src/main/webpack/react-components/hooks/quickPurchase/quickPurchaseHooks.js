import apolloClient from "../../../services/graphql.service";
import { useMutation } from "@apollo/react-hooks";
import { MAKE_PAYMENT_QUICK_PURCHASE_QUERY } from "../../queries/quickPurchaseQueries";
import Logger from "../../../services/logger.service";
import { setSpinnerActionDispatcher } from "../../store/spinner/spinnerActions";

export const useQuickPurchasePayment = () => {
  const [quickPurchasePayment] = useMutation(
    MAKE_PAYMENT_QUICK_PURCHASE_QUERY,
    {
      client: apolloClient,
      fetchPolicy: "no-cache",
      onCompleted: () => {
        setSpinnerActionDispatcher(false);
      },
      onError: (error) => {
        setSpinnerActionDispatcher(false);
        Logger.error(error);
      }
    }
  );
  return quickPurchasePayment;
};
