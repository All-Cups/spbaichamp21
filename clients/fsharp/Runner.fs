namespace SpbAiChamp

open System
open System.IO
open System.Net.Sockets

module Runner =
    type T(host, port, token: string) =
        let client = new TcpClient(host, port)
        let stream = new BufferedStream(client.GetStream())
        let reader = new BinaryReader(stream)
        let writer = new BinaryWriter(stream)
        let tokenData = System.Text.Encoding.UTF8.GetBytes token

        do
            client.NoDelay <- true
            writer.Write tokenData.Length
            writer.Write tokenData
            writer.Write 0
            writer.Write 4
            writer.Write 0
            writer.Flush()

        member this.run =
            let myStrategy = new MyStrategy()
            // let debugInterface = new DebugInterface(reader, writer)

            let rec loop () =
                match Codegame.ServerMessage.readFrom reader with
                | Codegame.ServerMessage.GetAction message ->
                    (Codegame.ClientMessage.ActionMessage { Action = myStrategy.getAction(message.PlayerView) }).writeTo writer
                    writer.Flush()
                    loop ()
                | Codegame.ServerMessage.Finish message -> ()
                | Codegame.ServerMessage.DebugUpdate message ->
                    // myStrategy.debugUpdate (message.PlayerView, debugInterface)
                    (new Codegame.ClientMessageDebugUpdateDone()).writeTo writer
                    writer.Flush()
                    loop ()

            loop ()

    [<EntryPoint>]
    let main argv =
        let host =
            match argv with
            | x when x.Length >= 1 -> x.[0]
            | _ -> "127.0.0.1"

        let port =
            match argv with
            | x when x.Length >= 2 -> x.[1] |> Int32.Parse
            | _ -> 31001

        let token =
            match argv with
            | x when x.Length >= 3 -> x.[2]
            | _ -> "0000000000000000"

        (new T(host, port, token)).run

        0 // return an integer exit code