package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import com.load.*;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServer_noVm_ServerStaysEmpty(){
		Server server = a(ServerBuilder.server().withCapacity(1));

		balancing(aServersListWith(server), anEmptyListOfVms());

		assertThat(server, hasCurrentLoadOf(0.0d));
	}


	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServersListWith(Server... servers){
		return servers;
	};

	private Server a(ServerBuilder builder){
		return builder.build();
	};
}
