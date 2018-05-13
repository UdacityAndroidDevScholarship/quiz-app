// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// Resource triggered firebase function
exports.sendNewResourceNotification = functions.database.ref('/resources/{resourceId}').onCreate((snapshot, context) => {

  const resource = snapshot.val();

  // if the data is empty exit from the function.
  if(!resource){
    return console.log('No data for resource');
  }

  // Only send notification for new resource not any update in the database
//   if(resource.previous.val()){
//     return;
//   }

  const resourceId = context.params.resourceId;
  console.log('New resource added to database. '+resourceId+'->' + resource.title);

  var payload = {
    data: {
      title: resource.title,
      description: resource.description,
      sender: resource['shared-by'],
      type: 'resources',
      action: resource.url,
      extra1: resource.category,
      extra2: resource.tags
    }
  };

  return admin.database().ref('/users').once('value').then(allUsers => {
    //console.log('all users: ' + allUsers);
    var tokens = new Array();
    allUsers.forEach(function(snapshot){
      var key = snapshot.key;
      var singleToken = snapshot.val()['fcm-token'];
      if(singleToken){
        tokens.push(singleToken);
      }
    });
    // var fcmTokens = allUsers.val().filter(user => {
    //     return user.email === 'drulabs@gmail.com';
    // }).map(user => {
    //     return user['fcm-token'];
    // });
    return admin.messaging().sendToDevice(tokens, payload).then(response => {
      return console.log('gcms sent successfully: ' + response);
    });
  });
});

exports.sendNewCommentNotification = functions.database.ref('/discussions/{quizId}/{commentId}').onCreate((snapshot, context) => {

    const comment = snapshot.val();
  
    // if the data is empty exit from the function.
    if(!comment){
      return console.log('No data for discussion comment');
    }
  
    // Only send notification for new resource not any update in the database
    //   if(resource.previous.val()){
    //     return;
    //   }
  
    const commentId = context.params.commentId;
    const quizId = context.params.quizId;

    console.log('New Comment added for. '+quizId+'->' + comment.comment);
  
    var payload = {
      data: {
        title: 'New Comment by ' + comment["comment-by"],
        description: comment.comment,
        sender: comment["comment-by"],
        type: 'discussion',
        action: quizId,
        extra1: comment["commenter-id"],
        extra2: commentId
      }
    };
  
    return admin.database().ref('/users').once('value').then(allUsers => {
      //console.log('all users: ' + allUsers);
      var tokens = new Array();
      allUsers.forEach(function(snapshot){
        var key = snapshot.key;
        var singleToken = snapshot.val()['fcm-token'];
        if(singleToken){
          tokens.push(singleToken);
        }
      });
      // var fcmTokens = allUsers.val().filter(user => {
      //     return user.email === 'drulabs@gmail.com';
      // }).map(user => {
      //     return user['fcm-token'];
      // });
      return admin.messaging().sendToDevice(tokens, payload).then(response => {
        return console.log('gcms sent successfully: ' + response);
      });
    });
  })