package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
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
		Server server = a(server().withCapacity(1));

		balancing(aServersListWith(server), anEmptyListOfVms());

		assertThat(server, hasCurrentLoadOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){
		Server server = a(server().withCapacity(1));
		Vm vm = a(vm().ofSize(1));
		balancing(aServersListWith(server), aVmsListWith(vm));

		assertThat(server, hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", server.contains(vm));
	}

	private Vm[] aVmsListWith(Vm... vm) {
		return vm;
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

	private VmBuilder vm(){
		return new VmBuilder();
	};
	
	private Vm a(VmBuilder builder){
		return builder.build();
	};
}
