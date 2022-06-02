const agent = require('superagent');
const statusCode = require('http-status-codes');
const chai = require('chai');

const { expect } = chai;
let itemId;
let item = {
  name: 'Miel',
  sellIn: 20,
  quality: 35,
  type: 'AGED'
};

it('Consume POST Service', async () => {
  const response = await agent.post('http://localhost:8080/api/items').send(item);

  expect(response.status).to.equal(statusCode.CREATED);
  expect(response.body).to.have.property('name').to.equal(item.name);
  expect(response.body).to.have.property('sellIn').to.equal(item.sellIn);
  expect(response.body).to.have.property('quality').to.equal(item.quality);
  itemId = response.body.id;
});

describe('gildedrose Api Tests', () => {
  it('Consume GET Service', async () => {
    const response = await agent.get('http://localhost:8080/api/items');
    expect(response.status).to.equal(statusCode.OK);
    response.body.forEach((itemInList) => {
      if (itemInList.id === itemId) {
        expect(itemInList.name).to.equal(item.name);
        expect(itemInList.sellIn).to.equal(item.sellIn);
        expect(itemInList.quality).to.equal(item.quality);
      }
    });
  });
});

it('Consume PUT Service', async () => {
  const newItem = {
    name: 'Miel',
    sellIn: 10,
    quality: 35,
    type: 'AGED'
  };

  const response = await agent.put(`http://localhost:8080/api/items/${itemId.toString()}`).send(newItem);

  expect(response.status).to.equal(statusCode.OK);
  expect(response.body).to.have.property('name').to.equal(newItem.name);
  expect(response.body).to.have.property('sellIn').to.equal(newItem.sellIn);
  expect(response.body).to.have.property('quality').to.equal(newItem.quality);
  item = newItem;
});

describe('gildedrose Api Tests', () => {
  it('Consume DELETE Service', async () => {
    const response = await agent.delete(`http://localhost:8080/api/items/${itemId.toString()}`);

    expect(response.status).to.equal(statusCode.OK);
  });
});
