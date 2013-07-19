FightController = ($scope, $http) ->
  $http.get('/fight/match.json').success (data) ->
    $scope.fight = data
    $http.get('/fight/gist/' + $scope.fight.left.name + ".html").success (html) ->
      $scope.left_gist = html
    $http.get('/fight/gist/' + $scope.fight.right.name + ".html").success (html) ->
      $scope.right_gist = html

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

TeamController = ($scope) ->
  $scope.show_bio = (name) ->
    $scope.bio_class = {};
    $scope.bio_class[name] = true;
