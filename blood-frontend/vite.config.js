import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    host: true
  },
  preview: {
    allowedHosts: [
      'blood-donation-system-production-e959.up.railway.app'
    ]
  }
})