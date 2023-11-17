import { SCOOTER_INFO } from "./scooterInfoTypes";

const initialState = {
  name: "",
  sku: "",
  sf_id: "",
  variants: [],
  selectedVariant: {}
};

/* Scooter Info Reducer */
export default function scooterInfoReducer(state = initialState, action) {
  switch (action.type) {
    case SCOOTER_INFO:
      return {
        ...state,
        name: action.payload.name,
        sku: action.payload.sku,
        sf_id: action.payload.sf_id,
        variants: action.payload.variants,
        selectedVariant: action.payload.selectedVariant
      };
    default:
      return state;
  }
}
