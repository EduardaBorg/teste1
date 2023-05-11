import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.sql.DriverManager.println

fun main() {
    val file = File("pokemon.csv")

    while (true) {
        println("Selecione a operação desejada:")
        println("1 - Criar tabela")
        println("2 - Ler tabela")
        println("3 - Editar tabela")
        println("4 - Adicionar linha à tabela")
        println("5 - Deletar linha da tabela")
        println("6 - Deletar tabela")
        println("7 - Sair")

        val opcao = readln().toIntOrNull() ?: continue //verifica se o usuário escreveu alguma coisa, se não tiver escrito o menu é chamado novamente

        when (opcao) {
            1 -> criarTabela(file)
            2 -> lerTabela(file)
            3 -> editarTabela(file)
            4 -> adicionarLinha(file)
            5 -> deletarLinha(file)
            6 -> deletarTabela(file)
            7 -> return
            else -> println("Opção inválida")
        }
    }
}


fun criarTabela(file: File) {
    val writer = FileWriter(file)

    writer.write("Numero,Nome,Tipo,Habilidade\n")
    writer.write("1,Bulbasaur,Planta,Overgrow\n")
    writer.write("2,Ivysaur,Planta,Overgrow\n")
    writer.write("3,Venusaur,Planta,Overgrow\n")
    writer.write("4,Charmander,Fogo,Blaze\n")
    writer.write("5,Charmeleon,Fogo,Blaze\n")
    writer.write("6,Charizard,Fogo,Blaze\n")
    writer.write("7,Squirtle,Água,Torrent\n")
    writer.write("8,Wartortle,Água,Torrent\n")
    writer.write("9,Blastoise,Água,Torrent\n")
    writer.write("10,Caterpie,Inseto,Run Away\n")

    writer.close()

    println("Tabela criada com sucesso")
}


fun editarTabela(file: File) {
    val reader = FileReader(file)

    val lines = reader.readLines().toMutableList()
    reader.close()

    println("Digite o nome do pokemon que deseja editar:")
    val nome = readlnOrNull() ?: return

    var index = -1
    for ((i, line) in lines.withIndex()) {
        val fields = line.split(",")
        if (fields[1] == nome) {
            index = i
            break
        }
    }

    if (index == -1) {
        println("Pokemon não encontrado")
        return
    }

    println("Digite o tipo do Pokemon:")
    val tipo = readlnOrNull() ?: return

    val fields = lines[index].split(",").toMutableList()
    fields[2] = tipo
    lines[index] = fields.joinToString(",")

    val writer = FileWriter(file)

    writer.write(lines.joinToString("\n"))

    writer.close()

    println("Pokemon editado com sucesso")
}

fun deletarLinha(file: File) {
    val reader = FileReader(file)

    val lines = reader.readLines().toMutableList()
    reader.close()

    println("Digite o nome do Pokemon que deseja deletar:")
    val nome = readlnOrNull() ?: return

    var index = -1
    for ((i, line) in lines.withIndex()) {
        val fields = line.split(",")
        if (fields[1] == nome) {
            index = i
            break
        }
    }

    if (index == -1) {
        println("Pokemon não encontrado")
        return
    }

    lines.removeAt(index)

    val writer = FileWriter(file)

    writer.write(lines.joinToString("\n"))

    writer.close()

    println("Pokemon deletado com sucesso")
}

fun adicionarLinha(file: File) {
    val reader = FileReader(file)

    val lines = reader.readLines().toMutableList()
    reader.close()

    println("Digite o numero do novo Pokemon:")
    val id = readlnOrNull() ?: return

    println("Digite o nome do novo Pokemon:")
    val nome = readlnOrNull() ?: return

    println("Digite o tipo do novo Pokemon:")
    val tipo = readlnOrNull() ?: return

    val novaLinha = "$id,$nome,$tipo"
    lines.add(novaLinha)

    val writer = FileWriter(file)

    writer.write(lines.joinToString("\n"))

    writer.close()

    println("Pokemon adicionado com sucesso")
}

fun lerTabela(file: File) {
    val reader = FileReader(file)

    val lines = reader.readLines()
    reader.close()

    if (lines.isEmpty()) {
        println("A tabela está vazia")
        return
    }

    for (line in lines) {
        val fields = line.split(",")
        val id = fields[0]
        val nome = fields[1]
        val idade = fields[2]
        println("$id\t| $nome\t| $idade")
    }
}

fun deletarTabela(file: File) {
    if (file.exists()) {
        file.delete()
        println("Tabela deletada com sucesso")
    } else {
        println("Tabela não encontrada")
    }
}