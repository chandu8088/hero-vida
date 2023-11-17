package com.heromotocorp.vida.core.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.Replicator;
import com.google.gson.GsonBuilder;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.CityMasterConfigService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.CityMasterVO;

/**
 * Osgi configuration to Twitter Configuration.
 *
 */
@Component(service = CityMasterConfigService.class, immediate = true)
public class CityMasterConfigServiceImpl implements CityMasterConfigService {
	
	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(CityMasterConfigServiceImpl.class);

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	public static final String CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV = "/content/dam/vida/config/City_Master.csv";

	@Override
	public void  processCityMasterJson() {
		String location = globalConfig.cityMasterCSVLocationPath() == null
				? CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV
				: globalConfig.cityMasterCSVLocationPath();
		List<CityMasterVO> cities = new ArrayList<>();
		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
		Resource assetResource = resolver.getResource(location);
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
			String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(cities);
			
			log.info(jsonString);
			log.info("Saving JSOn in DAM location at " + globalConfig.productBranchesUrl());
			if (!jsonString.isEmpty()) {
				CommonUtils.createDamAsset(jsonString, resolver, globalConfig.productBranchesUrl());
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, globalConfig.productBranchesUrl(), replicator);
			}
		}
		
	}

	/**
	 * @param line
	 * @return
	 */
	private static String[] getRecordFromLine(String line) {
		LOG.info("Parameters for getRecordFromLine method-\t" + line);
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(Constants.COMA);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return Arrays.copyOf(values.toArray(), values.toArray().length, String[].class);
	}

}
