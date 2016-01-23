angular.module( 'ngBoilerplate', [
  'templates-app',
  'templates-common',
  'ngBoilerplate.home',
  'ngBoilerplate.about',
  'ngBoilerplate.account',
  'ngBoilerplate.dashboard',
  'ngBoilerplate.release',
  'ngBoilerplate.action',
  'ui.router',
  'hateoas',
  'ngResource'
])

.config( function myAppConfig ( $stateProvider, $urlRouterProvider, HateoasInterceptorProvider ) {
  $urlRouterProvider.otherwise( '/home' );
  HateoasInterceptorProvider.transformAllResponses();
})

.run( function run () {
})

.controller( 'AppCtrl', function AppCtrl ( $scope, $location ) {
  $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
    if ( angular.isDefined( toState.data.pageTitle ) ) {
      $scope.pageTitle = toState.data.pageTitle + ' | Release Detailer' ;
    }
  });
})

;

