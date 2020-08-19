package com.wadhara;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordResult;
import com.amazonaws.services.kinesisfirehose.model.Record;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirehoseApp {
    List<String> productList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        FirehoseApp app = new FirehoseApp();
        app.sendData();
    }



    public void sendData() throws InterruptedException {
        createCatalog();
        //create client
        AmazonKinesisFirehose firehoseClient = KinesisFirehoseClient.getFirehoseClient();
        for(int i=0; i<100;i++){
            //create put record request
            PutRecordRequest putRecordRequest = new PutRecordRequest();
            putRecordRequest.setDeliveryStreamName("orders");
            putRecordRequest.setRecord(new Record().withData(ByteBuffer.wrap(orderList().getBytes())));
            //send data
            PutRecordResult result = firehoseClient.putRecord(putRecordRequest);
            System.out.println(result.toString());
            Thread.sleep(10000);
        }

    }

    private String orderList(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<1000;i++){
            sb.append(Math.abs(random.nextInt())).append(",")
                    .append(productList.get(random.nextInt(productList.size()))).append(",")
                    .append(random.nextInt(100)).append("\n");
        }
        return sb.toString().trim();
    }


    private void createCatalog(){
        productList.add("shirt");
        productList.add("t-shirt");
        productList.add("tie");
        productList.add("shorts");
        productList.add("jeans");
        productList.add("flip-flops");
        productList.add("shoes");
    }

}
