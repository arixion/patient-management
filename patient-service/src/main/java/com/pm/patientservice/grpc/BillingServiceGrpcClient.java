package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {

    private static final Logger logger = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

    private final BillingServiceGrpc.BillingServiceBlockingStub clientStub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String address,
            @Value("${billing.service.grpc.port:9001}") int port
    ) {
        logger.info("Connecting to Billing service GRPC service at {}:{}", address, port);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext().build();
        clientStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    /**
     *
     * @param patientId
     * @param name
     * @param email
     * @return
     */
    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setEmail(email)
                .setName(name)
                .build();
        BillingResponse response = clientStub.createBillingAccount(request);
        logger.info("Received response from Billing Service via GRPC : {}", response);
        return response;
    }
}
