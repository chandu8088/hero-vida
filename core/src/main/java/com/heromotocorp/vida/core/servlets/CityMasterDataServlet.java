package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.vo.CityMasterVO;

/**
 * Servlet that Co all the States and Cities into the response based on the
 * Country . Servlet can be accessed using via any page using selector
 * mastergeodata.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "vida/components/page", methods = HttpConstants.METHOD_GET, selectors = "city-master", extensions = "json")
@ServiceDescription("Master Product Info Servlet")
public class CityMasterDataServlet extends SlingSafeMethodsServlet {

	public static final String CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV = "/content/dam/vida/config/City_Master.csv";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3592470025905082741L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory
			.getLogger(CityMasterDataServlet.class);

	private static final String COMMA_DELIMITER = ",";

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;
	
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
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException,
			IOException {
		
		String location = globalConfig.cityMasterCSVLocationPath() == null ? CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV
				: globalConfig.cityMasterCSVLocationPath();

		List<CityMasterVO> cities = new ArrayList<>();
		
		Resource assetResource = req.getResourceResolver()
				.getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		if (original != null) {

			InputStream stream = original.getStream();
			List<String[]> records = new ArrayList<>();
			try (java.util.Scanner scanner = new java.util.Scanner(stream);) {
				while (scanner.hasNextLine()) {
					records.add(getRecordFromLine(scanner.nextLine()));
				}
				records.remove(0);
				for (String[] record : records) {
					
						CityMasterVO vo = new CityMasterVO();
						vo.setCityName(record[0]);
						vo.setStateName(record[1]);
						vo.setCountryName(record[2]);
						vo.setLatitde(record[3]);
						vo.setLongitude(record[4]);
						vo.setId();
						cities.add(vo);
					}
			}
			String jsonString= new Gson().toJson(cities);
			resp.getWriter().write(jsonString);
		}
	}

	/**
	 * @param line
	 * @return
	 */
	private static String[] getRecordFromLine(String line) {
		log.info("Parameters for getRecordFromLine method-\t" + line);
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		String[] strArr = Arrays.copyOf(values.toArray(),
				values.toArray().length, String[].class);
		return strArr;
	}

}
