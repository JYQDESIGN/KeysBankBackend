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
    private Long idAccount;
    private Long idCategory;
    private String label;
    private String type;
    private String color;

    private List<SubCategory> subCategories;

    public static List<Category> getDefaultCategories() {
        String jsonString = """
        [
          {
             "label": "Indéfinie",
             "type": "NONE",
             "subCategories": [],
             "color": "#000000"
          }, {
             "label": "Epargne",
             "type": "SAVING",
             "color": "#70E060",
             "subCategories": [
                {
                   "label": "Livret (A|LDDS|LEP)",
                   "type": "SAVING",
                   "keys": [],
                   "color": "#70E060"
                }, {
                   "label": "Logement (PEL|CEL)",
                   "type": "SAVING",
                   "keys": [],
                   "color": "#70E080"
                }, {
                   "label": "Assurance vie",
                   "type": "SAVING",
                   "keys": [],
                   "color": "#70E0A0"
                }, {
                   "label": "Retraite (PER|PERCO)",
                   "type": "SAVING",
                   "keys": [],
                   "color": "#70E0C0"
                }
             ]
          }, {
             "label": "Etude",
             "type": "SURVIVAL",
             "color": "#20EEE0",
             "subCategories": [
                {
                   "label": "Etude superieur",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#20EEE0"
                }, {
                   "label": "Frais de scolarité",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#40EEE0"
                }, {
                   "label": "Voyage scolaire",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#60EEE0"
                }, {
                   "label": "Transport",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#80EEE0"
                }, {
                   "label": "Matériels",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#A0EEE0"
                }, {
                   "label": "Permis",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#C0EEE0"
                }, {
                   "label": "Autres",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#E0EEE0"
                }
             ]
          }, {
             "label": "Transport",
             "type": "NONE",
             "color": "#0000ff",
             "subCategories": [
                {
                   "label": "Essence",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#0060FF"
                }, {
                   "label": "Remboursement prêt",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#0080ff"
                }, {
                   "label": "Assurance",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#00A0FF"
                }, {
                   "label": "Equipement/Réparations",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#00C0FF"
                }, {
                   "label": "Parking",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#00E0FF"
                }, {
                   "label": "Péage",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#00ffff"
                }, {
                   "label": "Autres",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#008080"
                }
             ]
          }, {
             "label": "Alimentation",
             "type": "NONE",
             "color": "#208080",
             "subCategories": [
                {
                   "label": "Retrait liquide",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#408000"
                }, {
                   "label": "Course|Epicerie",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#608000"
                }, {
                   "label": "Restaurant|Bar",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#808000"
                }, {
                   "label": "Restauration collective",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#4fecf7"
                }
             ]
          }, {
             "label": "Loisirs",
             "type": "OPTIONAL",
             "color": "#AA1030",
             "subCategories": [
                {
                   "label": "Vacances",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#AA3030"
                }, {
                   "label": "CD|DVD|Livre|Revue",
                   "type": "CULTURAL",
                   "keys": [],
                   "color": "#AA5030"
                }, {
                   "label": "Sortie|Spectacle|Musée",
                   "type": "CULTURAL",
                   "keys": [],
                   "color": "#AA7030"
                }, {
                   "label": "Sports",
                   "type": "CULTURAL",
                   "keys": [],
                   "color": "#AA8030"
                }, {
                   "label": "Abonnement Internet|Mobile",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#AAA030"
                }, {
                   "label": "Abonnement TV|Music",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#AAC030"
                }, {
                   "label": "Cadeaux",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#AAE030"
                }, {
                   "label": "Activités|Sport",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#BB1030"
                }, {
                   "label": "Argent de poche",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#CC1030"
                }, {
                   "label": "Tabac",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#DD1030"
                }, {
                   "label": "Autres",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#EE1030"
                }
             ]
          }, {
             "label": "Habillement",
             "type": "NONE",
             "color": "#407010",
             "subCategories": [
                {
                   "label": "Vêtements",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#407030"
                }, {
                   "label": "Chaussures",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#407050"
                }, {
                   "label": "Coiffeur",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#407070"
                }, {
                   "label": "Parfum",
                   "type": "OPTIONAL",
                   "keys": [],
                   "color": "#407090"
                }
             ]
          }, {
             "label": "Habitation",
             "type": "NONE",
             "color": "#80DD10",
             "subCategories": [
                {
                   "label": "Remboursement prêt",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#80DD30"
                }, {
                   "label": "Equipement|Mobilier|Déco",
                   "type": "EXTRA",
                   "keys": [],
                   "color": "#80DD50"
                }, {
                   "label": "EDF|GDF|EAU",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#80DD70"
                }, {
                   "label": "Entretien",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#80DD90"
                }, {
                   "label": "Banque",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#80DDB0"
                }, {
                   "label": "Assurance",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#80DDD0"
                }
             ]
          }, {
             "label": "Santé",
             "type": "NONE",
             "color": "#2055D0",
             "subCategories": [
                {
                   "label": "Médecine",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#4055D0"
                }, {
                   "label": "Pharmacie",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#6055D0"
                }, {
                   "label": "Optique",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#8055D0"
                }, {
                   "label": "Dentiste",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#A055D0"
                }, {
                   "label": "Assurance",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#C055D0"
                }, {
                   "label": "Mutuelle",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#E055D0"
                }, {
                   "label": "Rbt Sécu|Mutuelle",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#FF55D0"
                }
             ]
          }, {
             "label": "Impôt",
             "type": "NONE",
             "color": "#20FF20",
             "subCategories": [
                {
                   "label": "Impôt revenu",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#20F020"
                }, {
                   "label": "Impôt locaux|foncier",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#20D020"
                }, {
                   "label": "Amendes|PV|Taxes",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#20A020"
                }, {
                   "label": "Autre",
                   "type": "SURVIVAL",
                   "keys": [],
                   "color": "#208020"
                }
             ]
          }, {
             "label": "Revenus",
             "type": "INCOME",
             "color": "#3070FF",
             "subCategories": [
                {
                   "label": "Salaire",
                   "type": "INCOME",
                   "keys": [],
                   "color": "#0e62eb"
                }, {
                   "label": "Allocation",
                   "type": "INCOME",
                   "keys": [],
                   "color": "#f90f0f"
                }, {
                   "label": "Avance tiers",
                   "type": "NONE",
                   "keys": [],
                   "color": "#17efd8"
                }, {
                   "label": "Remboursements",
                   "type": "NONE",
                   "keys": [],
                   "color": "#d233eb"
                }, {
                   "label": "Revenus annexes",
                   "type": "INCOME",
                   "keys": [],
                   "color": "#13e21b"
                }
             ]
          }
        ]
        """;

        return convertJsonToEntities(jsonString);
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
