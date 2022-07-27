package resources
import models.{Item, Product}
import utils.Generators.getRandomDate

trait Data {
  val namesList = List(
    "Emma",
    "Olivia",
    "Ava",
    "Isabella",
    "Sophia",
    "Charlotte",
    "Mia",
    "Amelia",
    "Harper",
    "Evelyn",
    "Abigail",
    "Emily",
    "Elizabeth",
    "Mila",
    "Ella",
    "Avery",
    "Sofia",
    "Camila",
    "Aria",
    "Scarlett",
    "Victoria",
    "Madison",
    "Luna",
    "Grace",
    "Chloe",
    "Penelope",
    "Layla",
    "Riley",
    "Zoey",
    "Nora",
    "Lily",
    "Eleanor",
    "Hannah",
    "Lillian",
    "Addison",
    "Aubrey",
    "Ellie",
    "Stella",
    "Natalie",
    "Zoe",
    "Leah",
    "Hazel",
    "Violet",
    "Aurora",
    "Savannah",
    "Audrey",
    "Brooklyn",
    "Bella",
    "Claire",
    "Skylar",
    "Lucy",
    "Paisley",
    "Everly",
    "Anna",
    "Caroline",
    "Nova",
    "Genesis",
    "Emilia",
    "Kennedy",
    "Samantha",
    "Maya",
    "Willow",
    "Kinsley",
    "Naomi",
    "Aaliyah",
    "Elena",
    "Sarah",
    "Ariana",
    "Allison",
    "Gabriella",
    "Alice",
    "Madelyn",
    "Cora",
    "Ruby",
    "Eva",
    "Serenity",
    "Autumn",
    "Adeline",
    "Hailey",
    "Gianna",
    "Valentina",
    "Isla",
    "Eliana",
    "Quinn",
    "Nevaeh",
    "Ivy",
    "Sadie",
    "Piper",
    "Lydia",
    "Alexa",
    "Josephine",
    "Emery",
    "Julia",
    "Delilah",
    "Arianna",
    "Vivian",
    "Kaylee",
    "Sophie",
    "Brielle",
    "Madeline",
    "Peyton",
    "Rylee",
    "Clara",
    "Hadley",
    "Melanie",
    "Mackenzie",
    "Reagan",
    "Adalynn",
    "Liliana",
    "Aubree",
    "Jade",
    "Katherine",
    "Isabelle",
    "Natalia",
    "Raelynn",
    "Maria",
    "Athena",
    "Ximena",
    "Arya",
    "Leilani",
    "Taylor",
    "Faith",
    "Rose",
    "Kylie",
    "Alexandra",
    "Mary",
    "Margaret",
    "Lyla",
    "Ashley",
    "Amaya",
    "Eliza",
    "Brianna",
    "Bailey",
    "Andrea",
    "Khloe"
  )

  val addressList: List[String] = List(
    "Lisbon",
    "Porto",
    "Aveiro",
    "Beja",
    "Braga",
    "Bragança",
    "Castelo Branco",
    "Coimbra",
    "Évora",
    "Faro",
    "Funchal",
    "Guarda",
    "Leiria",
    "Ponta Delgada",
    "Portalegre",
    "Santarém",
    "Setúbal",
    "Viana do Castelo",
    "Vila Real",
    "Viseu"
  )

  val product1: Product = Product(
    "Bottle of water",
    "Drinks",
    0.3d,
    0.14d,
    getRandomDate
  )
  val item1: Item = Item(
    0.05d,
    0.08d,
    0.15d,
    product1
  )

  val product2: Product = Product(
    "Bread",
    "Food",
    0.7d,
    0.50d,
    getRandomDate
  )
  val item2: Item = Item(
    0.15d,
    0.08d,
    0.15d,
    product2
  )
  val product3: Product = Product(
    "Meat",
    "Food",
    1.5d,
    5.50d,
    getRandomDate
  )
  val item3: Item = Item(
    0.30d,
    0.10d,
    0.18d,
    product3
  )
  val product4: Product = Product(
    "Deodorant",
    "Hygiene",
    0.3d,
    1.20d,
    getRandomDate
  )
  val item4: Item = Item(
    0.10d,
    0.08d,
    0.18d,
    product4
  )
  val product5: Product = Product(
    "Beer",
    "Drinks",
    0.3d,
    0.14d,
    getRandomDate
  )
  val item5: Item = Item(
    0.03d,
    0.12d,
    0.23d,
    product5
  )
  val product6: Product = Product(
    "Sugar",
    "Food",
    1.5d,
    0.87d,
    getRandomDate
  )
  val item6: Item = Item(
    0.03d,
    0.05d,
    0.18d,
    product6
  )

  val itemsList: List[Item] = List(item1, item2, item3, item4, item5, item6)

}
