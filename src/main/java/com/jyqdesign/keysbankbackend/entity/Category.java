package com.jyqdesign.keysbankbackend.entity;
import java.util.ArrayList;
import java.util.List;

import com.jyqdesign.keysbankbackend.entity.dto.CategoryDTO;
import com.jyqdesign.keysbankbackend.entity.dto.SubCategoryDTO;
import lombok.Data;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Data
public class Category {
    private String idCategory;
    private String label;
    private String type;
    private String color;

    private List<SubCategory> subCategories;

    public static List<Category> getDefaultCategories() {
        String jsonString = """
[
      {
         "id": "a4f5f63b-713e-46e6-987b-e367f1cecfaa",
         "label": "Indéfinie",
         "type": "NONE",
         "subCategories": [],
         "color": "#000000"
      },
      {
         "id": "2ccf604c-3651-455d-855a-ccd35456a76c",
         "label": "Epargne",
         "type": "SAVING",
         "subCategories": [
            {
               "label": "Livret A",
               "type": "SAVING",
               "keys": [
                  "EPARGNE",
                  "FR76144452020000757523419"
               ],
               "color": "#74e663",
               "id": "6000146c-bca1-4ee2-bf63-1476d4ab1510"
            }
         ],
         "color": "#74e663"
      },
      {
         "id": "300d7e97-9f9a-4749-b1c0-4c5953d89d0b",
         "label": "Etude",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Etude superieur ASS",
               "type": "CULTURAL",
               "keys": [
                  "PENSION"
               ],
               "color": "#8cf765",
               "id": "09bfa890-7899-4cf5-8ddc-0d9b036962e0"
            },
            {
               "label": "Frais de scolarité",
               "type": "CULTURAL",
               "keys": [
                  "LYCEE",
                  "SKILL AND YOU"
               ],
               "color": "#eb4343",
               "id": "1b7d158c-1549-4c2c-83d8-e37b668a23a3"
            },
            {
               "label": "Cantine",
               "type": "SURVIVAL",
               "keys": [
                  "IZLY/S-MONEY"
               ],
               "color": "#efe711",
               "id": "33e61de0-9d7e-489b-bb11-bf49d2a8775b"
            },
            {
               "label": "Voyage scolaire",
               "type": "EXTRA",
               "keys": [],
               "color": "#f9960f",
               "id": "41a3d415-975e-449f-a460-40fcedd16d6d"
            },
            {
               "label": "Transport",
               "type": "SURVIVAL",
               "keys": [],
               "color": "#0becf1",
               "id": "6657ca57-465d-4ab6-9b40-646a8ca40c02"
            },
            {
               "label": "Matériels",
               "type": "EXTRA",
               "keys": [
                  "OFFICE DEPOT"
               ],
               "color": "#165c31",
               "id": "e131413c-a18b-43ff-bfe6-72a42dfddb1e"
            },
            {
               "label": "Permis",
               "type": "EXTRA",
               "keys": [
                  "50166160858"
               ],
               "color": "#f45bf5",
               "id": "a581c8b2-92ca-4ad2-a986-0c7fb7b60ad8"
            }
         ],
         "color": "#22ede0"
      },
      {
         "id": "3a9db70e-7afa-4f26-87de-e153cca46e2d",
         "label": "Transport",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Essence",
               "type": "SURVIVAL",
               "keys": [
                  "INTERMARCHE DA",
                  "REL. ST GREGOI",
                  "LECLERC ESSENC",
                  "REL.ELF",
                  "ESSFLOREALYG30",
                  "STATION LECLER",
                  "STATION DAC",
                  "CITROEN",
                  "TOTAL",
                  "AUTOMOB",
                  "AVIA"
               ],
               "color": "#0060ff",
               "id": "a1832160-a323-4908-8443-d1275cc3951d"
            },
            {
               "label": "Remboursement prêt",
               "type": "SURVIVAL",
               "keys": [
                  "50166588355",
                  "03153581750361312510",
                  "03304686150361312510",
                  "03585008950267505944",
                  "03535284650361312510",
                  "03928939950361312510",
                  "03900622350267505944",
                  "03849499550361312510",
                  "04140449850267505944",
                  "04795361150267505944",
                  "04741191250361312510",
                  "04712291250267505944",
                  "502675",
                  "503613",
                  "CLIO3",
                  "08758082050561293551",
                  "08917998650561293551",
                  "08998029650561293551",
                  "505612"
               ],
               "color": "#0080ff",
               "id": "979b26d2-d248-4fae-aeef-a430b89e54b0"
            },
            {
               "label": "Assurance",
               "type": "SURVIVAL",
               "keys": [
                  "GMF"
               ],
               "color": "#00a0ff",
               "id": "ed1814e2-1372-4f16-97fa-f69f8ddb1772"
            },
            {
               "label": "Equipement/Réparations",
               "type": "EXTRA",
               "keys": [
                  "SARL GEHANNIN",
                  "NORAUTO",
                  "AUTO BETTON",
                  "FINANCO",
                  "ACT.AUTO",
                  "WEST LAVAGE",
                  "DAFY",
                  "PNEU",
                  "SUMUP MECA",
                  "STELLANTIS",
                  "FEU VERT"
               ],
               "color": "#00c0ff",
               "id": "e2535399-adf6-471e-b1c9-fea139451850"
            },
            {
               "label": "Parking",
               "type": "OPTIONAL",
               "keys": [
                  "CITEDIA",
                  "IZIPARK",
                  "REGIE PARKING",
                  "HORODATEURS",
                  "QPARK"
               ],
               "color": "#00e0ff",
               "id": "e0f7aaf4-0e7e-4d35-85ac-d366f56217a8"
            },
            {
               "label": "Péage",
               "type": "EXTRA",
               "keys": [
                  "AUTOROUTES",
                  "Autoroutes du Sud"
               ],
               "color": "#00ffff",
               "id": "9f62b8fe-734d-43e4-9cc3-b3cead6a448d"
            },
            {
               "label": "Autres",
               "type": "EXTRA",
               "keys": [
                  "TAN",
                  "RATP",
                  "KEOLIS"
               ],
               "color": "#008080",
               "id": "b9b37c92-49a0-40b1-b4bf-ec1a8e2dc87b"
            }
         ],
         "color": "#0000ff"
      },
      {
         "id": "2932b17c-d670-4b70-ae27-7a88bbc7236b",
         "label": "Alimentation",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Retrait liquide",
               "type": "SURVIVAL",
               "keys": [
                  "DAB",
                  "GAB",
                  "OPERATION CARTE"
               ],
               "color": "#8fe76f",
               "id": "365c6511-a030-4f49-928a-b7905a509f65"
            },
            {
               "label": "Epicerie",
               "type": "SURVIVAL",
               "keys": [
                  "CARREFOURMARKE",
                  "DOUZ'AROME",
                  "LECLERC",
                  "GEANT",
                  "ITALIE GOURMAN",
                  "CORA",
                  "STOKOMANI",
                  "MARCHE PLUS",
                  "INTERMARCHE",
                  "CARREFOUR",
                  "SARL LES DELIC",
                  "SUPER U",
                  "DELICES DU TRE",
                  "BOUCH DU TREGO",
                  "BOUCH CHARC",
                  "ITALIE GOURM",
                  "LE CAMION A PI",
                  "PIZZA SPRINT",
                  "DOUZ AROME",
                  "KUSMI TEA",
                  "SODIRENNES TPL",
                  "TRAITEUR JOLY",
                  "AISON TROADEC",
                  "MAISON LARNICO",
                  "AUGUSTIN",
                  "BRIOCHE DOREE",
                  "THIRIET",
                  "NESPRESSO",
                  "BOULANGERIE",
                  "LES BLEU",
                  "CARVILLE",
                  "RECHAN INTERMA",
                  "LE FOURNIL DE",
                  "LE FRUITIER",
                  "LIDL",
                  "MOUALI",
                  "DA VIKEN",
                  "FERMIER DU COI",
                  "8 A HUIT",
                  "SAVEURS",
                  "DOMINO",
                  "GOURMETS",
                  "CAHUE",
                  "BOUCHERIE",
                  "BOURGUIGNON",
                  "PATISSERIE CHR",
                  "CRESCENDO",
                  "BOULANG LE DIO",
                  "DELICE TRINITA",
                  "PIZZERIA CASAM",
                  "fauveliere",
                  "QUARTIER",
                  "MAGASIN U",
                  "PALAIS DU FRUI",
                  "FROMAGEE",
                  "FOURNIL GRANGE",
                  "TRINITAIN",
                  "CELLIER LONGCH",
                  "PALAIS DES THE",
                  "GEFFRAULT",
                  "AugustinStGreg",
                  "FROGUIMY",
                  "ZESTE",
                  "LANDOLFI",
                  "2M FRUITS",
                  "FOU 2 POULET",
                  "CARVILL",
                  "CUISINE COLOMB",
                  "CUISINE ALMA",
                  "CAVE SAINT",
                  "YVAN CHEVALIER",
                  "JYGA",
                  "Augustin",
                  "FOURNIL",
                  "BIDAULT",
                  "NEKTAR",
                  "ATTRAPE DOUCEU",
                  "LHUMEAU",
                  "Le Palais des",
                  "JEFF DE BRUGES",
                  "PICARD",
                  "LANDOLF",
                  "Kusmi",
                  "ACHOURI",
                  "MESLE",
                  "GESBERT"
               ],
               "color": "#44de98",
               "id": "76cf55d1-3344-4590-bc78-4bec54174487"
            },
            {
               "label": "Restaurant",
               "type": "OPTIONAL",
               "keys": [
                  "MAC",
                  "LE QUATRE SAIS",
                  "BUFFALO",
                  "SARRAZINE",
                  "CASINOCAF",
                  "PIZZA SUD",
                  "AMORINO",
                  "MC DONALD'S",
                  "FLUNCH",
                  "MEZZO DI PASTA",
                  "MC DONALD S",
                  "VILLA MODENA",
                  "LA BOUCHERIE",
                  "COMPT VENITIEN",
                  "COT  BOEUF",
                  "RYCGOOD",
                  "LA TERRASSE",
                  "MAURICE",
                  "LA HUBLAIS",
                  "DEL ARTE",
                  "WHITEFIELDS",
                  "KER SOAZIG",
                  "WAFF FACTORY",
                  "SNC PLOUHINEC",
                  "L ITALIANO",
                  "LE GRILL",
                  "TUK TUK MUM",
                  "LA BRIOCHE DOR",
                  "LA PTITE FLAMB",
                  "BURGER KING",
                  "EPI DE BLE",
                  "dusudalouest",
                  "DUPONT",
                  "BINIOU",
                  "GREEN SUR MESU",
                  "AUTHENTIK",
                  "LE MOLE",
                  "3B",
                  "EPICAP",
                  "HAAGEN DAZ",
                  "LUPA",
                  "SAINTE ANNE",
                  "CHEZ PAUL",
                  "TRIBUNE",
                  "BARON ROUGE",
                  "COCOTTE",
                  "DONALD",
                  "PETITE OURSE",
                  "GUINGUETTE",
                  "BACCHUS",
                  "LES RELAIS D A",
                  "GUE MARY",
                  "L ATELIER",
                  "IL RISTORANTE",
                  "BUVETTE",
                  "PETITE FLAM",
                  "ONOLULU",
                  "TY MARIE",
                  "COIN DU MON",
                  "GREGOIRE",
                  "BDS"
               ],
               "color": "#f7c839",
               "id": "c770416c-027d-4c20-9627-4eb0650f1ba3"
            },
            {
               "label": "Cantine",
               "type": "SURVIVAL",
               "keys": [
                  "RIE CALYDO",
                  "ANS801319CALY1",
                  "MELCHIOR"
               ],
               "color": "#4fecf7",
               "id": "a9e2ad1c-3cca-41b3-a392-917b3577ba3e"
            }
         ],
         "color": "#169736"
      },
      {
         "id": "8caf45a3-1f9c-48e4-b99f-1f1634daba19",
         "label": "Loisirs",
         "type": "OPTIONAL",
         "subCategories": [
            {
               "label": "Vacances",
               "type": "OPTIONAL",
               "keys": [
                  "SNCF INTERNET",
                  "VACANCES DIREC",
                  "AIR FRANCE",
                  "AIRBNB",
                  "SNCF",
                  "VPG",
                  "VOLOTEA",
                  "VERYCHIC",
                  "SIXT",
                  "EASYJET",
                  "COINS SECRETS",
                  "TRANSAVIA"
               ],
               "color": "#eb1fd3",
               "id": "03b892f6-16f4-4ecb-8d8d-c8acb2372b41"
            },
            {
               "label": "Culture CD DVD CDRom Livre",
               "type": "CULTURAL",
               "keys": [
                  "VIRGIN",
                  "CULTURA",
                  "FNAC",
                  "AMAZON",
                  "FORUM RENNES",
                  "FORUM DU LIVRE"
               ],
               "color": "#104af7",
               "id": "f006a90b-19ba-427e-b8bb-3b9c61fc7a8e"
            },
            {
               "label": "Sorties Spectacles",
               "type": "CULTURAL",
               "keys": [
                  "BOWLING",
                  "THEATRE ED. VI",
                  "PANDA-TICKET",
                  "KALIDEA",
                  "FRANCE BILLET"
               ],
               "color": "#26f111",
               "id": "1875c902-6833-43ad-bbb0-baceda8c36eb"
            },
            {
               "label": "Sports",
               "type": "CULTURAL",
               "keys": [],
               "color": "#f3740b",
               "id": "11ebdb4c-1b89-4b7d-affd-6256b7135fb3"
            },
            {
               "label": "Abonnement internet",
               "type": "SURVIVAL",
               "keys": [
                  "HautDebit"
               ],
               "color": "#ea0cf5",
               "id": "94e9fbdc-d871-4b8a-b436-848d42c5cbb3"
            },
            {
               "label": "Abonnement TV",
               "type": "OPTIONAL",
               "keys": [
                  "CANAL",
                  "CANALSAT",
                  "CANASATELLITE",
                  "Netflix.com",
                  "NETFLIX",
                  "Deezer",
                  "Disney"
               ],
               "color": "#8a49ef",
               "id": "7f631bf6-4396-4efc-9a4d-c60148c2db2f"
            },
            {
               "label": "Abonnement revue",
               "type": "OPTIONAL",
               "keys": [
                  "PRISMA",
                  "MILAN PRESSE",
                  "BAYARD PRESSE",
                  "SCIENCE ET  VIE",
                  "MONDADORI",
                  "REWORLD MEDIA MAG"
               ],
               "color": "#f1df08",
               "id": "bda24211-47d5-4d54-b745-ac780db0f4ad"
            },
            {
               "label": "Téléphonie mobile",
               "type": "SURVIVAL",
               "keys": [
                  "ORANGE",
                  "RECHARGE ORANG",
                  "FreeMobile",
                  "FREE MOBILE",
                  "Orange",
                  "SFR mobile",
                  "SFR"
               ],
               "color": "#49dea1",
               "id": "52f547f7-3f08-4e4e-995f-cebaff82ae62"
            },
            {
               "label": "Location DVD",
               "type": "OPTIONAL",
               "keys": [
                  "CINEBANK"
               ],
               "color": "#802b79",
               "id": "158ea418-79b0-4aef-9c2c-1d6b5d4e42f3"
            },
            {
               "label": "Cadeaux",
               "type": "EXTRA",
               "keys": [
                  "FLEURS AND CO",
                  "POT AUX ROSES",
                  "FLEUR D O",
                  "KING JOUET",
                  "KIABI",
                  "VERTBAUDET.FR",
                  "NATURE DECOUVE",
                  "KINGJOUET"
               ],
               "color": "#27cea0",
               "id": "e5e67d68-6f9a-4194-b086-d2bf5bf3d707"
            },
            {
               "label": "Activités",
               "type": "EXTRA",
               "keys": [],
               "color": "#9fd638",
               "id": "d19dc83f-8c24-40aa-8ea4-fbe16e0e7dce"
            },
            {
               "label": "Argent de poche",
               "type": "EXTRA",
               "keys": [
                  "ANAIS",
                  "ADRIEN"
               ],
               "color": "#da52c3",
               "id": "29e5f8e1-a1fd-4f32-8c7d-5c8d32e22225"
            },
            {
               "label": "Informatique",
               "type": "EXTRA",
               "keys": [
                  "PIXMANIA",
                  "OVH"
               ],
               "color": "#079112",
               "id": "9c9a0b44-d100-454f-b94a-e3c2170dc180"
            },
            {
               "label": "Photo",
               "type": "EXTRA",
               "keys": [
                  "MYPIXMANIA"
               ],
               "color": "#dbe612",
               "id": "5af38022-c2c6-4bb1-a6c4-f070eabef4c2"
            },
            {
               "label": "Tabac",
               "type": "OPTIONAL",
               "keys": [
                  "TABAC",
                  "SNC LE TREGOR",
                  "AUBINE JERO",
                  "DRUBEN",
                  "BECAN"
               ],
               "color": "#26ca25",
               "id": "284f0927-53c1-414f-bade-9afe11b958ee"
            },
            {
               "label": "Divers",
               "type": "OPTIONAL",
               "keys": [],
               "color": "#916f08",
               "id": "5fda71e3-e395-4312-8cec-a941158f2d95"
            }
         ],
         "color": "#e6d731"
      },
      {
         "id": "6d1159bc-54b6-42b5-bb6d-7cd7ac8993ac",
         "label": "Habillement",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Vêtements",
               "type": "OPTIONAL",
               "keys": [
                  "JULES",
                  "BZB",
                  "DECATHLON",
                  "SPRINGFIELD",
                  "CACHE CACHE",
                  "LA HALLE",
                  "PROMOD",
                  "ETAM",
                  "CAMAIEU",
                  "CAMAIEURENNES",
                  "ZARA",
                  "H & M",
                  "REDOUTE",
                  "CASSIDY",
                  "PULL & BEAR",
                  "VANNERIE VASSE",
                  "PIMKIE",
                  "OKAIDI",
                  "NAF NAF",
                  "VENTE PRIVEE",
                  "SUPER SPORT",
                  "CABANA JEAN",
                  "DEVRED",
                  "CELIO",
                  "UNDIZ",
                  "DARJEELING",
                  "NAFNAF",
                  "GO SPORT",
                  "BRICE",
                  "H   M",
                  "MANGO",
                  "ASOS",
                  "BONOBO",
                  "TATI",
                  "PULL AND BEAR",
                  "BEAR",
                  "JUL",
                  "IZAC",
                  "HOLLISTER",
                  "CARNET DE VOL",
                  "LAFAYETTE",
                  "KAPORAL",
                  "IONA",
                  "YODE",
                  "VENTE-PRIVEE.C",
                  "SACMANIA",
                  "F G M",
                  "5ASEC",
                  "ROUGEGORGE",
                  "CRAZY REPUBLIC",
                  "CAROLL",
                  "MAISON73",
                  "SERGENT MAJOR",
                  "SHEIN.COM",
                  "VALEGE",
                  "PARFOIS",
                  "5 A SEC"
               ],
               "color": "#eeef08",
               "id": "479bd106-e157-4e19-9716-8e338ec615e5"
            },
            {
               "label": "Chaussures",
               "type": "OPTIONAL",
               "keys": [
                  "BESSEC",
                  "ERAM",
                  "CHAUSSURES BAT",
                  "SARENZA",
                  "ANDRE",
                  "SISTER MODE",
                  "MINELLI",
                  "COURIR"
               ],
               "color": "#e90b0b",
               "id": "fc7061b9-4c15-40d8-a434-32564c2b07b3"
            },
            {
               "label": "Coiffeur",
               "type": "SURVIVAL",
               "keys": [
                  "GERMAIN",
                  "HAIR",
                  "PROVOST",
                  "BACKSTAGE",
                  "ALIZE BEAUTE",
                  "ONGLES ET CIE"
               ],
               "color": "#0f20e7",
               "id": "233a2350-9b3f-4c36-85e1-7be3d7f182cd"
            },
            {
               "label": "Parfum",
               "type": "OPTIONAL",
               "keys": [
                  "YVES ROCHER",
                  "BEAUTY SUCCESS",
                  "JAINA",
                  "NOCIBE",
                  "SEPHORA"
               ],
               "color": "#17ed0c",
               "id": "29f2eabc-ad41-4f23-b941-e7259372d4ff"
            }
         ],
         "color": "#318ce6"
      },
      {
         "id": "ab4bcc91-fb84-47d0-b517-a7e6f6bcbce2",
         "label": "Habitation",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Remboursement prêt",
               "type": "SURVIVAL",
               "keys": [
                  "ALIANCE",
                  "CREDIT FONCIER",
                  "CFF"
               ],
               "color": "#f37009",
               "id": "0f8fe242-deb2-4b63-a118-73d528e6f63d"
            },
            {
               "label": "Equipement Mobilier Déco",
               "type": "EXTRA",
               "keys": [
                  "LEROY MERLIN",
                  "MAISONS DU MON",
                  "ALINEA",
                  "COCKTAIL SCAND",
                  "BRICODEPOTRENN",
                  "ELECTRO DEPOT",
                  "ROGARAY",
                  "MAG VERT",
                  "CASTORAMA",
                  "CASTORS",
                  "JARDI CAP MALO",
                  "IKEA",
                  "DBDC",
                  "BRICO DEPOT",
                  "CENTRAKOR",
                  "ONEY BANQUE ACCOR",
                  "JARDILAND",
                  "BOULANGER",
                  "MANOMANO",
                  "EPINA",
                  "SOCOOC",
                  "504662",
                  "MAX PLUS",
                  "LEROY MER",
                  "ADEO ADEO",
                  "Boulanger.com",
                  "DARTY",
                  "FETECIFETECA",
                  "SASDD20241202000000000000",
                  "LAVERIE",
                  "Boulanger"
               ],
               "color": "#200bf1",
               "id": "0d8d06e6-0795-4a7b-9437-cfa2afe2102f"
            },
            {
               "label": "Eau",
               "type": "SURVIVAL",
               "keys": [
                  "VEOLIA"
               ],
               "color": "#0bf11d",
               "id": "6c00ac5f-0934-4a00-8047-111847fe2084"
            },
            {
               "label": "EDF GDF",
               "type": "SURVIVAL",
               "keys": [
                  "EDF",
                  "GDF SUEZ",
                  "ENGIE",
                  "Engie",
                  "ELECTRICITE DE FR"
               ],
               "color": "#0ee7eb",
               "id": "73ce4649-bcd3-45de-9e1d-b5103b88c2d9"
            },
            {
               "label": "Chauffage",
               "type": "SURVIVAL",
               "keys": [
                  "SOLFEA",
                  "ENERGIE FOREST"
               ],
               "color": "#f107df",
               "id": "8941001e-a428-4737-ae08-34571ce9c200"
            },
            {
               "label": "Téléphone",
               "type": "SURVIVAL",
               "keys": [
                  "TELECOM"
               ],
               "color": "#eef915",
               "id": "72c15d3e-eaef-49e4-a0c1-b7991d76ef7f"
            },
            {
               "label": "Banque",
               "type": "SURVIVAL",
               "keys": [
                  "ADISPO",
                  "AGIOS",
                  "CARTE CASINO",
                  "COTISATION",
                  "BANQUE CASINO",
                  "PRIME DE FIDELITE",
                  "COMMISSION",
                  "FRAIS CHEQUE DE BANQUE",
                  "UTILISATION DU DECOUVERT"
               ],
               "color": "#f79208",
               "id": "a570d7ea-b348-4e92-bc0f-d78fd12fef3e"
            },
            {
               "label": "Assurance",
               "type": "SURVIVAL",
               "keys": [],
               "color": "#0b77f3",
               "id": "74486126-07ba-4290-9006-fd543d5c2669"
            }
         ],
         "color": "#e631c0"
      },
      {
         "id": "cbd5fde6-4c08-4388-b573-7f9619c4cbba",
         "label": "Santé",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Médecine",
               "type": "SURVIVAL",
               "keys": [
                  "DR LECOQ",
                  "DR ROUMANE",
                  "DOCTEUR GAUDIN",
                  "KINEPERF",
                  "DOCTEUR",
                  "DILLINGER"
               ],
               "color": "#da3c3c",
               "id": "c5fe94a3-31e6-48cc-992b-9ec6ee2dd6db"
            },
            {
               "label": "Pharmacie",
               "type": "SURVIVAL",
               "keys": [
                  "BLAYAU",
                  "PHIE",
                  "PHCIE",
                  "PHARMA LONGS C",
                  "PHARMA ROBINE",
                  "PHARMA",
                  "BEAUGENDR",
                  "PHAR ROUE BLAY",
                  "PHARM"
               ],
               "color": "#1bed58",
               "id": "bc3cb72c-3295-4255-adbf-7c05612f69f2"
            },
            {
               "label": "Optique",
               "type": "SURVIVAL",
               "keys": [
                  "WACHOWIAK",
                  "KRYS"
               ],
               "color": "#0c14ed",
               "id": "bdb0c84f-8151-430e-bb98-784db01dfff4"
            },
            {
               "label": "Dentiste",
               "type": "SURVIVAL",
               "keys": [
                  "LAGADEC"
               ],
               "color": "#0dd9f1",
               "id": "461760ed-7ecd-4645-8526-b3ed9104adea"
            },
            {
               "label": "Assurance",
               "type": "SURVIVAL",
               "keys": [
                  "PRELEVEMENT DE LA MUTUELLE"
               ],
               "color": "#ebe30e",
               "id": "a3710fad-8000-4557-b809-6e799577405e"
            },
            {
               "label": "Mutuelle",
               "type": "SURVIVAL",
               "keys": [
                  "PREVOYANCE",
                  "PRELEVEMENT DE MUTUELLE GENERALE",
                  "PREVIALYS",
                  "MUTUELLE GENER MG"
               ],
               "color": "#f37712",
               "id": "30fcec93-f051-4caa-b789-d0c23a5a2ef4"
            },
            {
               "label": "Rbt Sécu Mutuelle",
               "type": "SURVIVAL",
               "keys": [
                  "PREVADIES",
                  "C.P.A.M.",
                  "VIREMENT DE LA MUTUELLE GENERALE",
                  "HARMONIE MUTUELLE"
               ],
               "color": "#f30beb",
               "id": "1cded3a1-b5e2-445a-80ba-49bd480f2217"
            }
         ],
         "color": "#e6315b"
      },
      {
         "id": "b6b19c0e-ab53-450c-a404-bd17517d7322",
         "label": "Impôt",
         "type": "NONE",
         "subCategories": [
            {
               "label": "Impôt revenu",
               "type": "SURVIVAL",
               "keys": [
                  "1E067000035006M13510033424",
                  "CREDIMPOT"
               ],
               "color": "#ed0e0e",
               "id": "9a4942e7-cf63-4099-8079-86a3b6d83332"
            },
            {
               "label": "Impôt locaux",
               "type": "SURVIVAL",
               "keys": [
                  "1E067000035006M33500980882",
                  "1E067000035055M2352742863"
               ],
               "color": "#0dcaeb",
               "id": "34edaa10-6619-4dbe-becb-5a7f20425c7f"
            },
            {
               "label": "Impôt foncier",
               "type": "SURVIVAL",
               "keys": [
                  "1E067000035006M23527428638",
                  "1E067000035055M3350098088"
               ],
               "color": "#43e715",
               "id": "3d6e021e-6c4e-4ae5-b58b-4f6f37c85b38"
            },
            {
               "label": "Autre",
               "type": "SURVIVAL",
               "keys": [],
               "color": "#f5c611",
               "id": "565eedcb-c151-4727-bd3c-a2f9a39040ea"
            }
         ],
         "color": "#e9a547"
      },
      {
         "id": "9226612d-a095-44fb-ac58-f5b9730aee21",
         "label": "Revenus",
         "type": "INCOME",
         "subCategories": [
            {
               "label": "Salaire",
               "type": "INCOME",
               "keys": [
                  "THOMSON GRASS VALLEY",
                  "DOTC",
                  "THOMSON VIDEO",
                  "LA POSTE PAIE",
                  "HARMONIC",
                  "CNP PREF RETRAITE"
               ],
               "color": "#0e62eb",
               "id": "50c8f5d3-ee9f-4744-a34e-52161f5122b6"
            },
            {
               "label": "Allocation",
               "type": "INCOME",
               "keys": [
                  "CAF"
               ],
               "color": "#f90f0f",
               "id": "00900d16-cb8d-4608-955f-099bf0ae3651"
            },
            {
               "label": "Avance tiers",
               "type": "NONE",
               "keys": [],
               "color": "#17efd8",
               "id": "0eadd04f-9f15-4581-9625-26f519302db0"
            },
            {
               "label": "Remboursements",
               "type": "NONE",
               "keys": [
                  "TERRASSE"
               ],
               "color": "#d233eb",
               "id": "e3332bc2-d21f-4da8-b14b-a5cbd1bc0e14"
            },
            {
               "label": "Revenus annexes",
               "type": "INCOME",
               "keys": [
                  "CE THOMSON BNPPARIBAS",
                  "OBERTHUR",
                  "Oberthur"
               ],
               "color": "#13e21b",
               "id": "7eed9032-0cd7-4e65-9ca6-5eeb799ae91d"
            }
         ],
         "color": "#1235e7"
      }
   ]
        """;

        return convertJsonToEntities(jsonString);

        //ObjectMapper mapper = new ObjectMapper();
        //return mapper.readValue(jsonString, new TypeReference<List<Category>>() {});
    }



    private static List<Category> convertJsonToEntities(String json) {

        ObjectMapper mapper = new ObjectMapper();

        // Lire JSON → DTO
        List<CategoryDTO> categoryDTOs =
                mapper.readValue(json, new TypeReference<List<CategoryDTO>>() {});

        // Convertir DTO → Entities
        List<Category> categories = new ArrayList<>();

        for (CategoryDTO dto : categoryDTOs) {

            Category category = new Category();
            category.setIdCategory(dto.getId());
            category.setLabel(dto.getLabel());
            category.setType(dto.getType());
            category.setColor(dto.getColor());

            List<SubCategory> subCategories = new ArrayList<>();

            if (dto.getSubCategories() != null) {
                for (SubCategoryDTO subDTO : dto.getSubCategories()) {

                    SubCategory sub = new SubCategory();
                    sub.setIdSubCategory(subDTO.getId());
                    sub.setIdCategory(dto.getId());
                    sub.setLabel(subDTO.getLabel());
                    sub.setType(subDTO.getType());
                    sub.setColor(subDTO.getColor());

                    List<SubCategoryKey> keys = new ArrayList<>();

                    if (subDTO.getKeys() != null) {
                        for (String keyLabel : subDTO.getKeys()) {

                            SubCategoryKey key = new SubCategoryKey();
                            key.setIdSubCategory(0); // sera défini après insertion DB
                            key.setKey(keyLabel);

                            keys.add(key);
                        }
                    }

                    sub.setKeys(keys);
                    subCategories.add(sub);
                }
            }

            category.setSubCategories(subCategories);
            categories.add(category);
        }

        return categories;
    }
}
