describe('Test du PizzaListController', function () {
  var ctrl;
  var http;

  beforeEach(angular.mock.module('pizzeria-website'));

  beforeEach(angular.mock.inject(function ($rootScope, $controller, $httpBackend) {
    const scope = $rootScope.$new();
    ctrl = $controller("PizzaListController as ctrl", {$scope: scope});
    http = $httpBackend;
  }));

  it('should load pizzas', function (done) {
    http.when('GET', 'http://localhost:8080/pizzas').respond([{
      "nom": "Royale",
      "code": "royale",
      "prix": 12,
      "categorie": "VIANDE",
      "imageUrl": "http://placehold.it/150x150",
      "id": 1
    }]);

    ctrl.findAllPizzas()
        .then(() => expect(ctrl.listePizzas.length).toEqual(1))
        .finally(done);

    http.flush();
  });

});
