package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm: vms) {
            addToLoadedServer(servers, vm);
        }
    }

    private void addToLoadedServer(Server[] servers, Vm vm) {
        Server lessLoaded = findLessLoadedServer(servers);
        lessLoaded.addVm(vm);
    }

    private Server findLessLoadedServer(Server[] servers) {
        Server lessLoaded = null;
        for (Server server : servers) {
            if (lessLoaded == null || lessLoaded.currentLoadPercentage > server.currentLoadPercentage) {
                lessLoaded = server;
            }
        }
        return lessLoaded;
    }
}
