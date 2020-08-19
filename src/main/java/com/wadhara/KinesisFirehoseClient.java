package com.wadhara;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;

public class KinesisFirehoseClient {

    public static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
    public static final String AWS_SECRET_KEY = "aws.secretKey";

    static {
        System.setProperty(AWS_ACCESS_KEY_ID, "AKIAV7ILO75272ZHEHFQ");
        System.setProperty(AWS_SECRET_KEY, "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");
    }

    public static AmazonKinesisFirehose getFirehoseClient(){
        return AmazonKinesisFirehoseClientBuilder.standard().withRegion(Regions.US_EAST_1)
                .build();
    }
}
