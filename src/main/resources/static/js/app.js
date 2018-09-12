var srsApp = angular.module("srsApp", ['angular-loading-bar'])
.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    
  }]);
var srsApp = angular.module("srsApp", [])
.config( [
    '$compileProvider',
    function( $compileProvider )
    {   
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|http|sip|mailto):/);
        // Angular before v1.2 uses $compileProvider.urlSanitizationWhitelist(...)
    }
]);