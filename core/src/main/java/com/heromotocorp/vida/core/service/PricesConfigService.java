package com.heromotocorp.vida.core.service;

/**
 * Service Interface that pulls Product Price data from SFDC  and Caches in AEM at DAM.
 *
 */ 
/**
 * @author shissriv
 *
 */
public interface PricesConfigService {

	/** Sample Response this would generate
	 * [
    {
        "city_state_id": "NEW DELHI~DELHI~INDIA",
        "variant_sku": "HFDLXBR",
        "price": "75,000",
        "exShowRoomPrice": "75,000",
        "onRoadPrice": "75,000",
        "minLeaseDownPayment": "25,000",
        "minLoanDonpayment": "25,000",
        "minLoanEMI": "5,000",
        "minLeaseEMI": "5,000",
        "loanOfferURL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0",
        "leaseOfferRL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0"
    
    },
   {
        "city_state_id": "NEW DELHI~DELHI~INDIA",
        "variant_sku": "HFDLXTB",
        "price": "65,000",
        "exShowRoomPrice": "65,000",
        "onRoadPrice": "85,000",
        "minLeaseDownPayment": "25,000",
        "minLoanDonpayment": "25,000",
        "minLoanEMI": "5,000",
        "minLeaseEMI": "5,000",
        "loanOfferURL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0",
        "leaseOfferRL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0"
    
    },
    {
        "city_state_id": "BANGALORE~KARNATKA~INDIA",
        "variant_sku": "SKU003",
        "price": "77,000",
        "exShowRoomPrice": "75,000",
        "onRoadPrice": "95,000",
        "minLeaseDownPayment": "25,000",
        "minLoanDonpayment": "25,000",
        "minLoanEMI": "5,000",
        "minLeaseEMI": "5,000",
        "loanOfferURL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0",
        "leaseOfferRL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0"

    },
   {
        "city_state_id": "BANGALORE~KARNATKA~INDIA",
        "variant_sku": "SKU001",
        "price": "61,000",
        "exShowRoomPrice": "65,000",
        "onRoadPrice": "65,000",
        "minLeaseDownPayment": "25,000",
        "minLoanDonpayment": "25,000",
        "minLoanEMI": "5,000",
        "minLeaseEMI": "5,000",
        "loanOfferURL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0",
        "leaseOfferRL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0"

    },
    {
        "city_state_id": "JAIPUR~RAJASTHAN~INDIA",
        "variant_sku": "MCODE HSPIFDRSCFIFSG",
        "price": "79,000",
        "exShowRoomPrice": "75,000",
        "onRoadPrice": "75,000",
        "minLeaseDownPayment": "25,000",
        "minLoanDonpayment": "25,000",
        "minLoanEMI": "5,000",
        "minLeaseEMI": "5,000",
        "loanOfferURL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0",
        "leaseOfferRL": "https://staging-portal.autovertplug.com/loan-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQkVOR0FMVVJVIiwiYXNzZXRzIjpbeyJza3UiOiJITV8wMDIiLCJkaXNwbGF5X25hbWUiOiJWaWRhIiwiZXhfc2hvd3Jvb21fcHJpY2UiOiI1MDAwMCIsIm9uX3JvYWRfcHJpY2UiOiI1MDAwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6IjAiLCJzdGF0ZV9zdWJzaWR5IjoiMCIsInNtYXJ0X2NhcmRfZmVlIjoiMCIsInJ0b19yZWdpc3RyYXRpb25fZmVlIjoiMCIsImRpc2NvdW50IjoiMCIsImNvbXByZWhlbnNpdmVfaW5zdXJhbmNlX3ByaWNlIjoiMCIsImFkZG9uX2luc3VyYW5jZV9wcmljZSI6IjAiLCJub25fdmVoaWNsZV9hbW91bnQiOiIwIiwicHJpY2luZ19ub3RlcyI6IklubnVhZ3JhbCBPZmZlcnMiLCJhY2Nlc3NvcmllcyI6W119XSwic2hpcHRvX2NpdHkiOiJCRU5HQUxVUlUifQ.ikFAq5dZ47ZEdK-nvGXyO3VOQYOtVm0cWeY0rH0mGW0"

    }
]
	 * 
	 * @return
	 */
	void processCityBasedPriceAndOffer();
}
