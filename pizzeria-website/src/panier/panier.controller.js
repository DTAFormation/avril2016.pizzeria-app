import { Pizza } from '../shared/model/pizza'

export class PanierController {

  constructor () {
    // Chaque ligne contient un objet avec la structure { 'pizza': (objet pizza ici), 'quantite': (entier nb de pizzas de la pizza fournie) }
    // TEMPORAIRE : contenu en dur, remplacer avec données en stockage local
    this.contenu = [
      { 'pizza': new Pizza({'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 },
      { 'pizza': new Pizza({'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 3 }
    ]
  }

  // remarque : juste une idée pour le multilangue (ne fonctionne pas)
  getTraductionPizza (pizza, langue) {
    // TEMPORAIRE ; remplacer par les données de traductions associées à la valeur langue
    var nomsPizzas = {
      'royale': 'Royale',
      'marguerita': 'Margherita'
    }

    // retourne soit le nom trouvé dans la base, soit le nom par défaut de la pizza.
    return nomsPizzas[pizza.code] | pizza.nom
  }

}
