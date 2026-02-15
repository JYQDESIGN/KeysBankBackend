package com.jyqdesign.keysbankbackend.entity;
import java.util.List;
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
                    "keys": [],
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
                    "keys": [],
                "color": "#8cf765",
                    "id": "09bfa890-7899-4cf5-8ddc-0d9b036962e0"
            },
            {
                "label": "Frais de scolarité",
                    "type": "CULTURAL",
                    "keys": [],
                "color": "#eb4343",
                    "id": "1b7d158c-1549-4c2c-83d8-e37b668a23a3"
            },
            {
                "label": "Cantine",
                    "type": "SURVIVAL",
                    "keys": [],
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
                    "keys": [],
                "color": "#165c31",
                    "id": "e131413c-a18b-43ff-bfe6-72a42dfddb1e"
            },
            {
                "label": "Permis",
                    "type": "EXTRA",
                    "keys": [],
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
                    "keys": [],
                "color": "#0060ff",
                    "id": "a1832160-a323-4908-8443-d1275cc3951d"
            },
            {
                "label": "Remboursement prêt",
                    "type": "SURVIVAL",
                    "keys": [],
                "color": "#0080ff",
                    "id": "979b26d2-d248-4fae-aeef-a430b89e54b0"
            },
            {
                "label": "Assurance",
                    "type": "SURVIVAL",
                    "keys": [],
                "color": "#00a0ff",
                    "id": "ed1814e2-1372-4f16-97fa-f69f8ddb1772"
            },
            {
                "label": "Equipement/Réparations",
                    "type": "EXTRA",
                    "keys": [],
                "color": "#00c0ff",
                    "id": "e2535399-adf6-471e-b1c9-fea139451850"
            },
            {
                "label": "Parking",
                    "type": "OPTIONAL",
                    "keys": [],
                "color": "#00e0ff",
                    "id": "e0f7aaf4-0e7e-4d35-85ac-d366f56217a8"
            },
            {
                "label": "Péage",
                    "type": "EXTRA",
                    "keys": [],
                "color": "#00ffff",
                    "id": "9f62b8fe-734d-43e4-9cc3-b3cead6a448d"
            },
            {
                "label": "Autres",
                    "type": "EXTRA",
                    "keys": [],
                "color": "#008080",
                    "id": "b9b37c92-49a0-40b1-b4bf-ec1a8e2dc87b"
            }
         ],
            "color": "#0000ff"
        }
        ]
        """;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, new TypeReference<List<Category>>() {});
    }
}
