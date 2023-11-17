import gql from "graphql-tag";

export const MAKE_PAYMENT_QUICK_PURCHASE_QUERY = gql`
  mutation quickPurchase($input: QuickReserveInput) {
    quickPurchase(input: $input) {
      message
      opportunityId
      success
      saleOrderId
      prebookingOrderNumber
    }
  }
`;
