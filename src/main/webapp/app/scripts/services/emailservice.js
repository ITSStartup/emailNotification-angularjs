'use strict';

angular.module('webappApp')
    .factory('EmailService', function($resource) {
     return $resource('../rest/email',{ },
     {
   
    }
    );
  });
