FightController = ($scope, $http) ->
  $scope.new_fight = () ->
    $http.get('/fight/match.json').success (data) ->
      $scope.fight = data

  $scope.full_left = () ->
    if ($scope.left_class == 'full_screen')
      $scope.left_class = ''
      $scope.right_class = ''
    else
      $scope.left_class = 'full_screen'
      $scope.right_class = 'hidden'

  $scope.full_right = () ->
    if ($scope.right_class == 'full_screen')
      $scope.left_class = ''
      $scope.right_class = ''
    else
      $scope.left_class = 'hidden'
      $scope.right_class = 'full_screen'

  $scope.win_left = () ->
    $http.get('/fight/win/left/' + $scope.fight.uniqueId).success () ->
      $scope.new_fight()

  $scope.win_right = () ->
    $http.get('/fight/win/right/' + $scope.fight.uniqueId).success () ->
      $scope.new_fight()

  $scope.new_fight()

TeamController = ($scope) ->
  $scope.show_bio = (name) ->
    $scope.bio_class = {};
    $scope.bio_class[name] = true;
