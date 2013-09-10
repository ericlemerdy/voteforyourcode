angular.module('codestory', ['ngRoute', 'ngAnimate'])
  .config(['$locationProvider', ($locationProvider) ->
    $locationProvider.html5Mode(true)
  ])
  .config(['$routeProvider', ($routeProvider) ->
    $routeProvider
      .when('/team/', {})
      .when('/team/:name', {templateUrl: 'partials/bio.html', controller: MemberController})
      .otherwise({redirectTo: '/team/'})
  ])

MemberController = ($scope, $routeParams) ->
  $scope.name = $routeParams.name
  $scope.partial = "/team/bio/#{$routeParams.name}.md"


