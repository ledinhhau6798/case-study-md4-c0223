class App {
    static DOMAIN_SERVER = window.origin;
    static API_SERVER = this.DOMAIN_SERVER + '/api';

    static API_CUSTOMER = this.API_SERVER + '/customers';
    static API_DEPOSIT = this.API_SERVER + '/deposits';
    static API_WITHDRAW = this.API_SERVER + '/withdraws';
    static API_TRANSFER = this.API_SERVER + '/transfers';
    static API_DELETE = App.API_CUSTOMER + '/delete'

    static API_LOCATION_REGION = 'https://vapi.vnappmob.com/api/province'

    static showDeleteConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure you want to delete the selected data ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it !',
            cancelButtonText: 'Cancel',
        });
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500,
        });
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        });
    }
}


class Product{
    constructor(id,title,price,unit,category,avatar) {
        this.id=id;
        this.title=title;
        this.price=price;
        this.unit=unit;
        this.category=category;
        this.avatar=avatar;
    }
}
class TableOrder{
    constructor(id,title,status) {
        this.id=id;
        this.title=title;
        this.status =status;
    }
}

// $(function() {
//     $(".num-space").number(true, 0, ',', ' ');
//     $(".num-point").number(true, 0, ',', '.');
//     $(".num-comma").number(true, 0, ',', ',');

//     $('[data-toggle="tooltip"]').tooltip();
// });
