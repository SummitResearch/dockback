Setup

    From the "rest_cli" folder, you should be able to do:

    npm install

Running HOST Commands

    Create a new host
        host\create_host.js <restHost> <dockerHost> <sshUser> <sshPassword>
     
    Get all hosts
        host\get_all_hosts.js <restHost>
        
    Get host by ID
        host\get_host.js <restHost> <dockerHostId>

    Delete host by ID
        host\delete_host.js <restHost> <dockerHostId>

    Update host by ID
        host\update_host.js <restHost> <dockerHostId> <dockerHost> <sshUser> <sshPassword>
        
    List images by dockerHost ID
        host\list_host_images.js <restHost> <dockerHostId>
        
    List containers by dockerHost ID
        host\list_host_containers.js <restHost> <dockerHostId>
        
        