MARI Farid, JOLY Brenann

# Initiation à l'Intelligence Artificielle

## Informations préalables
### Dépôt git : 
https://github.com/FaridMari/SAE_Initiation_IA

## I. Perceptrons multi-couches
### A. Un peu de programmation ?
#### a) Un squelette de code...

```
debut
/*
creation d'un MLP
*/
layers <- [3, 2, 1] // couche 1 : 3 neurones, couche 2 : 2 neurones, couche 3 : 1 neurone
learningRate <- 0.1 // taux d'apprentissage
transFunc <- Sigmoid() // fonction de transfert sigmoid
mlp <- Mpl(layers, learningRate, transFunc) // creation du MLP

/*
entrainement du MLP
*/
inputs <- [[0, 0], [0, 1], [1, 0], [1, 1]] // valeurs d'entrée XOR
outputs <- [1, 0, 0, 1] // valeurs de sortie XOR
tant que changement faire // boucle d'apprentissage
  pour i de 0 à input.length faire // pour chaque entrée
    mlp.backPropagate(inputs[i], outputs[i].toArray()) // apprentissage
  fin pour
fin tant que
fin
```
