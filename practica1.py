import argparse
ListaTokens =[]


def main():
    ListaTokens=[]
    linea=1
    parser = argparse.ArgumentParser(description="Ejemplo p programa con interfaz de linea de comandos")
    parser.add_argument("archivo", nargs="?", help="Archivo (opcional) de entrada")

    args = parser.parse_args()

    if args.archivo:
        with open(args.archivo, "r") as archivo:  #lee el archivo proporcionado
            lista_caracteres =[]

            caracter = archivo.read(1)
            while caracter:
                lista_caracteres.append(caracter)
                caracter = archivo.read(1)

            cadenaCompleta = ''.join(lista_caracteres)
            automataComentarios(linea, cadenaCompleta, ListaTokens)


        for i in range(0,len(ListaTokens),3):
            grupo = ListaTokens[i:i+3]
            print(grupo)


    else
        while True: #solicita las entradas repetidamente
            ListaTokens=[]
            entrada = input("Cadena o enter para salir:") #borrar cuando termine de probarse
            l = 1
            if entrada:
                s = automataComentarios(linea, entrada, ListaTokens)


            else:
                break

            for i in range(0, len(ListaTokens), 3):
                grupo = ListaTokens[i:i + 3]


if __name__ == "__main__":
    main()