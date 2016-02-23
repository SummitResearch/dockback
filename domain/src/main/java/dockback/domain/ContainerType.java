package dockback.domain;

public enum ContainerType {
    DOCKER("docker"), RKT("rkt"), LXC("lxc");

    private String type;

    ContainerType(String type) {

        this.type = type;
    }
}
