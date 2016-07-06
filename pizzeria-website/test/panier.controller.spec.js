import { Pizza } from '../src/shared/model/pizza'

const VERBOSE = false // mettre true pour afficher certaines sorties console

describe('Test: PanierController', function () {
  var ctrlPanier
  var ctrlPizza

  // -----------------------------------------------------
  // -- initialisations

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $componentController) {
    const scope = $rootScope.$new()
    ctrlPanier = $componentController('panierComponent', {$scope: scope})
    ctrlPizza = $componentController('pizzaList', {$scope: scope})
    ctrlPanier.clear()
  }))

  // -----------------------------------------------------
  // -- tests

  // panier vide lors de son initialisation
  it('should be empty right after initialization', function () {
    expect(ctrlPanier.isEmpty()).toEqual(true)
  })

  // insertion de pizzas dans le panier
  it('should insert pizzas in the basket', function () {
    var contenuPanier

    expect(ctrlPanier.size()).toEqual(0)
    if (VERBOSE) console.log('ctrlPanier.size() === ' + ctrlPanier.size())

    ctrlPizza.addPizza(new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrlPanier.size()).toEqual(1)
    if (VERBOSE) console.log('ctrlPanier.size() === ' + ctrlPanier.size())

    ctrlPizza.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrlPanier.size()).toEqual(2)
    if (VERBOSE) console.log('ctrlPanier.size() === ' + ctrlPanier.size())

    contenuPanier = ctrlPanier.findAllPizzas()
    expect(contenuPanier['marguerita'].quantite).toEqual(1)

    // identifiant non séquentiel (1 et 2 déjà pris, prendre quelque chose de non adjacent)
    ctrlPizza.addPizza(new Pizza({'id': 19, 'code': 'bolognaise', 'nom': 'Bolognaise', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrlPanier.size()).toEqual(3)
    if (VERBOSE) console.log('ctrlPanier.size() === ' + ctrlPanier.size())

    // pizza déjà existante : pas de changement de taille
    ctrlPizza.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrlPanier.size()).toEqual(3)
    if (VERBOSE) console.log('ctrlPanier.size() === ' + ctrlPanier.size())

    // ajouter test pour quantité pizza d'un type dans ctrlPanier
    contenuPanier = ctrlPanier.findAllPizzas()
    expect(contenuPanier['marguerita'].quantite).toEqual(2)

    // tester la quantité totale des pizzas tous types confondus
    var keys = Object.keys(contenuPanier)
    var somme = 0
    for (var i = 0; i < keys.length; i++) {
      somme += contenuPanier[keys[i]].quantite
    }
    expect(somme).toEqual(4)

  // insertion d'une pizza différente, mais ayant un identifiant déjà pris : exception (cas qui ne devrait pas se produire)
  // DÉSACTIVÉ - addPizza ne vérifie pas encore si la pizza fournie est identique ou non à celle ayant le même id
  // expect(() => {
  //   ctrlPanier.addPizza(new Pizza({'id': 2, 'code': 'fausse_marguerita', 'nom': 'Fausse margherita', 'prix': 20, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
  // }).toThrow()
  })

  // suppression d'une pizza du panier
  it('should delete an item from the basket', function () {
    var pizza1 = new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    var pizza2 = new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    var pizza3 = new Pizza({'id': 8, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    var pizza4 = new Pizza({'id': 13, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    ctrlPizza.addPizza(pizza1)
    ctrlPizza.addPizza(pizza2)
    ctrlPizza.addPizza(pizza3)
    ctrlPizza.addPizza(pizza4)
    console.log(pizza1.code)
    if (VERBOSE) console.log('ctrlPanier.size() === ', ctrlPanier.size())
    expect(ctrlPanier.size()).toEqual(4)

    // supprime 1 item
    if (VERBOSE) console.log('ctrlPanier.size() === ', ctrlPanier.size())
    ctrlPanier.deletePizza(pizza1)
    expect(ctrlPanier.size()).toEqual(3)

    // item déjà supprimé : toujours autant d'items
    if (VERBOSE) console.log('ctrlPanier.size() === ', ctrlPanier.size())
    ctrlPanier.deletePizza(pizza1)
    expect(ctrlPanier.size()).toEqual(3)

    // item déjà supprimé : toujours autant d'items
    if (VERBOSE) console.log('ctrlPanier.size() === ', ctrlPanier.size())
    ctrlPanier.deletePizza(pizza3)
    expect(ctrlPanier.size()).toEqual(2)
  })

  // incrementation d'une pizza du panier
  it('should increment an item from the basket', function () {
    let pizza = new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    ctrlPizza.addPizza(pizza)

    let panier = ctrlPanier.findAllPizzas()

    // incremente 1 item
    if (VERBOSE) console.log('panier[pizza.code].quantite === ', panier[pizza.code].quantite)
    ctrlPanier.incrementPizza(pizza)
    expect(panier[pizza.code].quantite).toEqual(2)

    // incremente encore une fois
    if (VERBOSE) console.log('panier[pizza.code].quantite === ', panier[pizza.code].quantite)
    ctrlPanier.incrementPizza(pizza)
    expect(panier[pizza.code].quantite).toEqual(3)
  })

  // décrementation d'une pizza du panier
  it('should decrement an item from the basket', function () {
    let pizza = new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    ctrlPizza.addPizza(pizza)

    let panier = ctrlPanier.findAllPizzas()

    if (VERBOSE) console.log('panier[pizza.code] === ', panier[pizza.code])
    expect(ctrlPanier.size()).toEqual(1)
    ctrlPanier.incrementPizza(pizza)
    expect(panier[pizza.code].quantite).toEqual(2)

    // decrement 1 item
    if (VERBOSE) console.log('panier[pizza.id].quantite === ', panier[pizza.id].quantite)
    ctrlPanier.decrementPizza(pizza)
    expect(ctrlPanier.size()).toEqual(1)
    expect(panier[pizza.code].quantite).toEqual(1)

    // decrement encore une fois et tombe a 0, supprime la ligne
    if (VERBOSE) console.log('panier[pizza.id].quantite === ', panier[pizza.code].quantite)
    ctrlPanier.decrementPizza(pizza)
    expect(ctrlPanier.size()).toEqual(0)
  })

  // vidage du panier
  it('should clean the basket', function () {
    ctrlPizza.addPizza(new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrlPizza.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrlPizza.addPizza(new Pizza({'id': 8, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrlPizza.addPizza(new Pizza({'id': 13, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}))

    if (VERBOSE) console.log('ctrlPanier.size() === ', ctrlPanier.size())
    expect(ctrlPanier.size()).toEqual(4)

    // vide le panier
    if (VERBOSE) console.log('ctrlPanier.size() === ', ctrlPanier.size())
    ctrlPanier.deleteAllPizzas()
    expect(ctrlPanier.size()).toEqual(0)
  })
})
