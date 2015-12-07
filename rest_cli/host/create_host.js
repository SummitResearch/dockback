var request = require('request');

if( process.argv.length != 6 ) {
    console.log( "Usage: create_host host sshUser sshPassword" );
} else {

    var restHost = process.argv[2];
    var dockerHost = process.argv[3];
    var sshUser = process.argv[4];
    var sshPassword = process.argv[5];

    console.log( "Creating new host for: " + dockerHost + " on " + restHost );

    var options = {
        uri: 'https://' + restHost + ':8443/host',
        method: 'POST',
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


