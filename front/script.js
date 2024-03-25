function calc(event, form) {
  event.preventDefault();

  // dados a serem enviados pela solicitação POST
  let data = {
    "items": [
      {
        "customer": form.customer_1.value,
        "description": form.description_1.value,
        "value": form.value_1.value
      },
      {
        "customer": form.customer_2.value,
        "description": form.description_2.value,
        "value": form.value_2.value
      },
      {
        "customer": form.customer_3.value,
        "description": form.description_3.value,
        "value": form.value_3.value
      }
    ],
    "deliveryTax": form.delivery_tax.value,
    "discount": form.discount.value,
    "additional": form.additional.value
  }

  fetch('http://localhost:8080/api/calculations', {
    method: "POST",
    body: JSON.stringify(data),
    headers: { "Content-type": "application/json; charset=UTF-8" }
  })
    .then(response => response.json())
    .then(json => mostrarResposta(json))
    .catch(err => console.log(err));

  function mostrarResposta(dados) {
    const lista = dados.valuePerCustomer;
    let mensagem = ``;

    for (i in lista) {
      mensagem += `
            Pessoa: ${lista[i].customer}
            Valor a pagar: ${lista[i].value.toFixed(2)}
            Link Pagamento: ${lista[i].link}

        `;
    }

    alert(mensagem);
  }
}