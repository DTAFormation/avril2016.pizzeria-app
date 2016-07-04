export class InscriptionController {

  constructor (ClientService, $location) {
    this.ClientService = ClientService
    this.$location = $location
    this.probleme = false
    console.log(this.probleme)
  }


  saveClient (form) {
    console.log(this.client.email)
    if (form.$invalid && this.probleme === true) return
    this.ClientService.saveClient(this.client)
      .then(() => {
        console.log(this.client)
        this.$location.path('/')
      })
      .catch(() => { this.probleme = true })
  }
  rechercheClient () {
    console.log(this.client.email)
    this.ClientService.rechercheClientByEmail(this.client.email)
      .then((data) => { 
        console.log(data.data, 'data.data')
        console.log(data, 'data')
        if (this.isEmpty(data.email)) {
          this.probleme = false
           console.log(this.probleme, 'problemefalse')
        }
        else {
          this.probleme = true
           console.log(this.probleme, 'problemetrue')
        }
        console.log(this.probleme, 'probleme')
      })
  }
  isEmpty (str) {
    return (!str || 0 === str.length);
  }
}

InscriptionController.$inject = ['ClientService', '$location']
