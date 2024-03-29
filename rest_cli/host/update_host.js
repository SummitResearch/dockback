var request = require('request');

if( process.argv.length != 8 ) {
    console.log( "Usage: update_host restHost dockerHostId dockerHost dockerPort sshUser sshPassword" );
} else {

    var restHost = process.argv[2];
    var dockerHostId = process.argv[3];
    var dockerHost = process.argv[4];
    var dockerPort = process.argv[5];
    var sshUser = process.argv[6];
    var sshPassword = process.argv[7];

    console.log( "Creating new host for: " + dockerHost + " on " + restHost );

    var options = {
        uri: 'https://' + restHost + ':8443/host/' + dockerHostId,
        method: 'PUT',
        json: {
            "hostname": dockerHost,
            "sshUser": sshUser,
            "sshPassword": sshPassword
        },
        headers: {
            'Authorization': 'Basic ' + new Buffer('admin:Awesome123!').toString('base64')
        },
        rejectUnauthorized: false
    };

    console.log( "\n\rUsing the options:\n\r" );
    console.log( options );
    console.log( "\n\r" );

    request( options, function(error, response, body ) {

        if( error ) {
            console.log( "\n\r Got an error! \n\r" );
            console.log( error );
            return;
        }

        console.log("Got " + response.statusCode);

        if( !error ) {
            console.log( body )
        }

    });
}


