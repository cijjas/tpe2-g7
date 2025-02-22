package ar.edu.itba.pod.tpe2.client.utils;

import ar.edu.itba.pod.tpe2.client.utils.cli_parsing.BaseArguments;
import ar.edu.itba.pod.tpe2.models.ticket.services.TicketFactory;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastConfig {

    public static HazelcastInstance configureHazelcastClient(BaseArguments arguments) {
        ClientConfig clientConfig = new ClientConfig();

        GroupConfig groupConfig = new GroupConfig()
                .setName(arguments.getClusterName())
                .setPassword(arguments.getClusterPass());

        clientConfig.setGroupConfig(groupConfig);

        ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();

        clientNetworkConfig.addAddress(arguments.getAddresses().split(";"));

        clientConfig.setNetworkConfig(clientNetworkConfig);

        // Nitro hazelast
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        serializationConfig.addDataSerializableFactory(arguments.getCity().ordinal(), new TicketFactory());

        clientConfig.getSerializationConfig().setAllowUnsafe(true);
        clientConfig.setProperty("hazelcast.client.max.concurrent.invocations", "10000");
        //clientConfig.setProperty("hazelcast.client.invocation.timeout.seconds", "200");

        return HazelcastClient.newHazelcastClient(clientConfig);
    }


}
