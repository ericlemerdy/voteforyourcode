FightController = ($scope) ->
  console.log('Start')

  $scope.left_class = '';
  $scope.right_class = '';

  $scope.full_left = () ->
    if ($scope.left_class == 'full_screen')
      $scope.left_class = ''
      $scope.right_class = ''
    else
      $scope.left_class = 'full_screen'
      $scope.right_class = 'hidden'

  $scope.full_right = () ->
    if ($scope.right_class == 'full_screen')
      $scope.right_class = ''
      $scope.left_class = ''
    else
      $scope.right_class = 'full_screen'
      $scope.left_class = 'hidden'
