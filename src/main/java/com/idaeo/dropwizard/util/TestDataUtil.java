package com.idaeo.dropwizard.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.idaeo.dropwizard.api.model.Building;
import com.idaeo.dropwizard.api.model.Tenant;

import java.io.IOException;

/**
 * @author cdock
 * Date: 1/26/14
 * Time: 5:26 PM
 */
public class TestDataUtil {
    static AmazonDynamoDBClient client;
    static DynamoDBMapper mapper;


    public static void main(String[] args) {

        try {
            //createClient();
            //uploadSampleTenants();
            //uploadSampleBuildings();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
//
//    private static void createClient() throws IOException {
//        AWSCredentials credentials = new PropertiesCredentials(
//                TestDataUtil.class.getResourceAsStream("/AwsCredentials.properties"));
//
//        client = new AmazonDynamoDBClient(credentials);
//        //client.setRegion(Region.getRegion(Regions.US_WEST_2));
//        client.setEndpoint("http://localhost:8000");
//
////        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig(
////                DynamoDBMapperConfig.SaveBehavior.CLOBBER,
////                null,
////                null,
////                null);
//
//
//        mapper = new DynamoDBMapper(client);
//    }
//
//    private static void uploadSampleTenants() {
//        Tenant t = new Tenant(null, "Bill Brasky", "bill@snl.com", "1234567890", 0);
//        mapper.save(t);
//    }
//
//    private static void uploadSampleBuildings() {
//        Building building = new Building(null, "2200 Westlake", "900 Lenora St.", "Seattle", "WA", "98121");
//        Building b = new Building(null, "2200 Westlak", "900 Lenora St.", "Seattle", "WA", "98121");
//        mapper.save(building); new Building();
//
//        building = new Building(null, "Westchester Executive","25 Westchester Dr.","Youngstown", "OH", "44515");
//        mapper.save(building);
//    }

}
