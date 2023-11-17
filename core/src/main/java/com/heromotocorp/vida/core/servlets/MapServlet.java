package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.crx.JcrConstants;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.CityMasterVO;

/**
 * Servlet that Populates City in Map dialog.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.VIDA_PATH)
@ServiceDescription("Map Servlet")
public class MapServlet extends SlingSafeMethodsServlet {
	
	/**
	 * globalConfig.
	 */
	@Reference
	private transient GlobalConfig globalConfig;

	/**
	 * Serial Version Id.
	 */
	private static final long serialVersionUID = -6982204520185679260L;

	/**
	 * CSV file Path for City details.
	 */
	public static final String CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV = "/content/dam/vida/config/City_Master.csv";

	/**
	 * Log constants.
	 */
	private static Logger log = LoggerFactory.getLogger(MapServlet.class);
	
	/**
	 * Do get.
	 *
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			List<KeyValue> dropDownList = new ArrayList<>();
			String location = globalConfig.cityMasterCSVLocationPath() == null
					? CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV
					: globalConfig.cityMasterCSVLocationPath();
			ResourceResolver resourceResolver = request.getResourceResolver();
			Resource assetResource = resourceResolver.getResource(location);
			Asset asset = assetResource.adaptTo(Asset.class);
			Rendition original = asset.getOriginal();

			if (original != null) {
				InputStream stream = original.getStream();
				List<String[]> records = new ArrayList<>();
				try (java.util.Scanner scanner = new java.util.Scanner(stream);) {
					while (scanner.hasNextLine()) {
						records.add(CommonUtils.getRecordFromLine(scanner.nextLine()));
					}
					records.remove(0);
					for (String[] record : records) {

						CityMasterVO vo = new CityMasterVO();
						vo.setCityName(record[0]);
						vo.setStateName(record[1]);
						vo.setCountryName(record[2]);
						vo.setId();
						dropDownList.add(new KeyValue(vo.getCityName(), vo.getId()));

					}
				}
			}

			@SuppressWarnings("unchecked")
			DataSource ds = new SimpleDataSource(new TransformIterator(dropDownList.iterator(), input -> {
				KeyValue keyValue = (KeyValue) input;
				ValueMap vm = new ValueMapDecorator(new HashMap<>());
				vm.put("text", keyValue.key);
				vm.put("value", keyValue.value);
				return new ValueMapResource(resourceResolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, vm);
			}));
			request.setAttribute(DataSource.class.getName(), ds);
		} catch (Exception e) {
			log.error("Error in Get Drop Down Values", e);
		}
	}

	private class KeyValue {

		/**
		 * key property.
		 */
		private String key;
		/**
		 * value property.
		 */
		private String value;

		/**
		 * constructor instance intance.
		 *
		 * @param newKey   -
		 * @param newValue -
		 */
		private KeyValue(final String newKey, final String newValue) {
			this.key = newKey;
			this.value = newValue;
		}
	}
}