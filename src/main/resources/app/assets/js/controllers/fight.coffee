angular.module 'codestory', []

FightController = ($scope, $http) ->
  $scope.new_fight = () ->
    $http.get('/fight/match.json').success (data) ->
      $scope.fight = data

  $scope.full_left = () ->
    [$scope.left_class, $scope.right_class] = $scope.full($scope.left_class)

  $scope.full_right = () ->
    [$scope.right_class, $scope.left_class] = $scope.full($scope.right_class)

  $scope.full = (current) ->
    if (current is 'full_screen') then ['', ''] else ['full_screen', 'hidden']

  $scope.win_left = () ->
    $http.get("/fight/win/left/#{$scope.fight.uniqueId}").success () ->
      $scope.new_fight()

  $scope.win_right = () ->
    $http.get("/fight/win/right/#{$scope.fight.uniqueId}").success () ->
      $scope.new_fight()

  $scope.new_fight()
