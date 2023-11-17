package com.heromotocorp.vida.core.service;

/**
 * Interface Service to get Product Master data from SFDC.
 *
 */
/**
 * @author shissriv
 *
 */
public interface ProductMasterConfigService {

	/** 
	 *  "items": [
        {
            "name": "HF Deluxe",
            "sku": "HFDLX",
            "sf_id": "a128c000003hGz8AAE",
            "__typename": "ConfigurableProduct",
            "variants": [
                {
                    "__typename": "SimpleProduct",
                    "name": "Blazing Red",
                    "sku": "HFDLXBR",
                    "range": "85 kms",
                    "charging_time": "3.5 Hrs",
                    "accelerator": "5.9 Secs",
                    "top_speed": "80 km/h",
                    "sf_id": "a1W8c000007iMcwEAE",
                    "color": "Red"
                },
                {
                    "__typename": "SimpleProduct",
                    "name": "Techno Blue",
                    "sku": "HFDLXTB",
                    "range": "85 kms",
                    "charging_time": "3.5 Hrs",
                    "accelerator": "5.9 Secs",
                    "top_speed": "80 km/h",
                    "sf_id": "a1W8c000007iMcrEAE",
                    "color": "Blue"
                }
          ]  
	 * 
	 * @return
	 */
	void processProductMasterJson();
}
